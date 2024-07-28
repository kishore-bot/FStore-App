import {
  BadRequestException,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Product } from './entity/product.entity';
import { Repository } from 'typeorm';
import { ProductQuery } from './dto/product_query';
import { Seller } from 'src/seller/entity/seller.entitty';
import { User } from 'src/user/entity/user.entitty';
import { Color } from './entity/color.entity';
import { Size } from './entity/size.entity';
import { CreateProductDto } from './dto/create_product.dto';
import { ColorService } from './color/color.service';
import { SizeService } from './size/size.service';
import { SubQueryDto } from './dto/sub_query.dto';
import { AwsService } from 'src/aws/aws.service';

@Injectable()
export class ProductService {
  constructor(
    @InjectRepository(Product)
    private readonly productRepo: Repository<Product>,
    private readonly colorService: ColorService,
    private readonly sizeService: SizeService,
    private readonly awsService:AwsService
  ) {}

  async create(productDto: CreateProductDto, seller: Seller) {
    const colors = await this.colorService.createColor(productDto.color);
    const sizes = await this.sizeService.createSize(productDto.size);
    const product = this.productRepo.create({
      ...productDto,
      colors,
      sizes,
      seller: seller,
    });
    await this.productRepo.save(product);
    return product;
  }

  // Needed to change Modifiy
  async update(
    id: number,
    productDto: Partial<Product>,
    seller: Seller,
  ): Promise<Product> {
    const product = await this.productRepo.findOne({
      where: { id },
      relations: ['seller'],
    });

    if (!product) {
      throw new NotFoundException(`Product with id ${id} not found`);
    }
    if (product.seller.id !== seller.id) {
      throw new BadRequestException(
        `You are not allowed to update this product`,
      );
    }
    const updatedProduct = this.productRepo.merge(product, productDto);
    await this.productRepo.save(updatedProduct);
    return updatedProduct;
  }

  async fetchCategories(query: SubQueryDto) {
    const categories = await this.productRepo
      .createQueryBuilder('product')
      .select('DISTINCT product.category', 'category')
      .where('product.mainCategory = :mainCategory', {
        mainCategory: query.mainCategory,
      })
      .andWhere('product.gender = :gender', { gender: query.gender })
      .orderBy('category', 'ASC')
      .getRawMany();

    const uniqueCategories = categories.map((category) => category.category);
    return uniqueCategories;
  }

  async fetchBrands(mainCategory: string) {
    const q = mainCategory.toLowerCase();
    const products = await this.productRepo.find({
      where: { mainCategory: q },
      relations: ['seller'],
    });
    const sellersMap = new Map<number, Seller>();
    products.forEach((product) => {
      sellersMap.set(product.seller.id, product.seller);
    });
    const uniqueSellers = Array.from(sellersMap.values());
    uniqueSellers.sort((a, b) => a.name.localeCompare(b.name));
    return uniqueSellers;
  }

  async fetchProductDetails(productId: number, user: User) {
    const product = await this.productRepo.findOne({
      where: { id: productId },
      relations: ['seller', 'favorites', 'favorites.user', 'colors', 'sizes', 'images'],
    });
  
    if (!product) {
      throw new NotFoundException('Product not found');
    }
  
    // Check if the product is marked as favorite by the user
    const isFav = product.favorites.some((favorite) => favorite.user.id === user.id);
    (product as any).favorite = isFav;
  
    // Apply discount if available
    const rate = product.discount;
    if (rate !== 0) {
      const price = product.price;
      const discount = 1 - rate / 100;
      product.price = price * discount;
    }
  
    // Get color and size names
    const colorNames = product.colors.map((color) => color.name);
    const sizeNames = product.sizes.map((size) => size.name);
    (product as any).color = colorNames;
    (product as any).size = sizeNames;
  
    // Get URLs for all product images
    const imageUrls = await Promise.all(
      product.images.map(async (image) => {
        const url = await this.awsService.getObjectUrl(image.key);
        return url;
      })
    );
    (product as any).imageUrls = imageUrls;
  
    return product;
  }
  

  async fetchByQuery(query: ProductQuery, user: User) {
    const queryBuilder = this.productRepo
      .createQueryBuilder('product')
      .leftJoinAndSelect('product.seller', 'seller')
      .leftJoinAndSelect(
        'product.favorites',
        'favorites',
        'favorites.userId = :userId',
        { userId: user.id },
      )
      .leftJoinAndSelect('product.colors', 'color')
      .leftJoinAndSelect('product.sizes', 'size');

    if (query.mainCategory) {
      queryBuilder.andWhere('product.mainCategory = :mainCategory', {
        mainCategory: query.mainCategory,
      });
    }

    if (query.category) {
      queryBuilder.andWhere('product.category = :category', {
        category: query.category,
      });
    }

    if (query.byNew !== 0) {
      queryBuilder.addOrderBy('product.createdAt', 'DESC');
    }

    if (query.byPopular !== 0) {
      queryBuilder.addOrderBy('product.popularity', 'DESC');
    }

    if (query.byRating !== 0) {
      queryBuilder.addOrderBy('product.rating', 'DESC');
    }

    if (
      query.lowPrice > 0 &&
      query.highPrice > 0 &&
      query.highPrice > query.lowPrice
    ) {
      queryBuilder.andWhere('product.price BETWEEN :lowPrice AND :highPrice', {
        lowPrice: query.lowPrice,
        highPrice: query.highPrice,
      });
    }

    if (query.byPriceSort) {
      if (query.byPriceSort === 1) {
        queryBuilder.addOrderBy('product.price', 'ASC');
      }
      if (query.byPriceSort === 2) {
        queryBuilder.addOrderBy('product.price', 'DESC');
      }
    }

    if (query.available !== undefined) {
      if (query.available === 1) {
        queryBuilder.andWhere('product.availability > 0');
      } else {
        queryBuilder.andWhere('product.availability >= 0');
      }
    }

    if (query.gender) {
      queryBuilder.andWhere('product.gender = :gender', {
        gender: query.gender,
      });
    }
    if (query.color) {
      queryBuilder
        .innerJoin('product.colors', 'colorFilter')
        .andWhere('colorFilter.name = :color', { color: query.color });
    }

    if (query.size) {
      queryBuilder
        .innerJoin('product.sizes', 'sizeFilter')
        .andWhere('sizeFilter.name = :size', { size: query.size });
    }

    if (query.name && query.name.length > 0) {
      const names = Array.isArray(query.name) ? query.name : [query.name];
      queryBuilder.andWhere('seller.name IN (:...names)', { names });
    }

    const total = await queryBuilder.getCount();
    const offset = (query.page - 1) * 5;
    queryBuilder.skip(offset).take(5);
    const products = await queryBuilder.getMany();

    const processedProducts = await Promise.all(products.map(async (product) => {
      const thumbnailUrl = product.thumbnail ? await this.awsService.getObjectUrl(product.thumbnail) : null;
      return {
        ...product,
        isFavorite: product.favorites && product.favorites.length > 0,
        thumbnailUrl,
      };
    }));
    return { total, products: processedProducts };
  }

  async productForOrder(productId: number, quantityNo: number) {
    const product = await this.productRepo.findOneBy({ id: productId });

    if (!product) {
      throw new Error('Product not found');
    }

    let adjustedQuantityNo = quantityNo;
    let newAvailability = product.availability - quantityNo;

    if (newAvailability < 0) {
      adjustedQuantityNo = product.availability;
      newAvailability = 0;
    }

    product.availability = newAvailability;
    product.popularity += 1;

    await this.productRepo.save(product);

    return { product, quantityNo: adjustedQuantityNo };
  }

  async fetchProduct(productId: number) {
    const product = await this.productRepo.findOne({
      where: { id: productId },
    });
    return product;
  }

  async productForReview(productId: number, rating: number) {
    const product = await this.productRepo.findOneBy({ id: productId });
    if (!product) {
      throw new Error('Product not found');
    }
    if (product.noOfRating === 0) {
      product.noOfRating = 1;
      product.rating = rating;
    } else {
      product.noOfRating += 1;
      product.rating =
        (product.rating * (product.noOfRating - 1) + rating) /
        product.noOfRating;
    }

    await this.productRepo.save(product);
    return product;
  }

  async forProductImages(productId: number, date: string) {
    const product = await this.productRepo.findOne({
      where: { id: productId },
    });
    product.thumbnail = `product/${product.id.toString()}/m${1}_${date}`;
    await this.productRepo.save(product);
    return product;
  }
}