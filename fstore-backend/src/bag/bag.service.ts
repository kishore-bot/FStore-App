import { Injectable } from '@nestjs/common';
import { Bag } from './entity/bag.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { User } from 'src/user/entity/user.entitty';
import { ProductService } from 'src/product/product.service';
import { CreateBagDto } from './dto/create_bag.dto';
import { UpdateQuantityDto } from './dto/update_qunatity.dto';
import { AwsService } from 'src/aws/aws.service';

@Injectable()
export class BagService {
  constructor(
    @InjectRepository(Bag) private readonly bagRepo: Repository<Bag>,
    private readonly productService: ProductService,
    private readonly awsService: AwsService,
  ) {}

  async createBag(bagDto: CreateBagDto, user: User) {
    const product = await this.productService.fetchProduct(bagDto.productId);
    if (!product || product.availability <= 0) {
      throw new Error('Product not found');
    }
    var price: number;
    if (bagDto.price == 0) {
      if (product.discount == 0) {
        price = product.price;
      } else {
        price = product.price - product.price * (product.discount / 100);
      }
    } else {
      price = bagDto.price;
    }
    const bag = this.bagRepo.create({
      product: product,
      user: user,
      quantity: 1,
      price: price,
      color: bagDto.color,
      size: bagDto.size,
    });
    await this.bagRepo.save(bag);
    return { message: 'Added to bag' };
  }

  async fetchBags(page: number, user: User) {
    const [bags, totalItems] = await this.bagRepo
      .createQueryBuilder('bag')
      .leftJoinAndSelect('bag.product', 'product')
      .where('bag.userId = :userId', { userId: user.id })
      .andWhere('product.availability > 0')
      .orderBy('bag.createdAt', 'DESC')
      .skip((page - 1) * 5)
      .take(5)
      .getManyAndCount();

      const processedBags = await Promise.all(
        bags.map(async (bag) => {
          const product = bag.product;
          const thumbnailUrl = product.thumbnail
            ? await this.awsService.getObjectUrl(product.thumbnail)
            : null;
  
          return {
            ...bag,
            product: {
              ...product,
              thumbnailUrl,
            },
          };
        }),
      );
    return {
      bags: processedBags,
      total: totalItems,
    };
  }

  async getTotalPrice(user: User) {
    const totalPriceResult = await this.bagRepo
      .createQueryBuilder('bag')
      .leftJoin('bag.product', 'product')
      .select('SUM(bag.price * bag.quantity)', 'sum')
      .where('bag.userId = :userId', { userId: user.id })
      .andWhere('product.availability > 0')
      .getRawOne();

    const price = parseFloat(totalPriceResult.sum) || 0;
    return { price };
  }

  async deleteBags(uid: number) {
    const bag = await this.bagRepo.findOne({ where: { id: uid } });
    if (!bag) {
      throw new Error('Bag not found');
    }
    await this.bagRepo.remove(bag);
    return { message: 'SuccessFully removed ' };
  }

  async updateQuantity(quantity: UpdateQuantityDto) {
    const bag = await this.bagRepo.findOne({ where: { id: quantity.id } });
    bag.quantity = quantity.quantity;
    await this.bagRepo.save(quantity);
    return { message: 'Updated Qunatity' };
  }


  async fetchBagsForOrders(user: User): Promise<Bag[]> {
    const bags = await this.bagRepo
      .createQueryBuilder('bag')
      .leftJoinAndSelect('bag.product', 'product')
      .where('bag.userId = :userId', { userId: user.id })
      .andWhere('product.availability > 0')
      .orderBy('bag.createdAt', 'DESC')
      .getMany();
    return bags;
  }

  async clearBagsForOrders(user: User): Promise<void> {
    await this.bagRepo.delete({ user: { id: user.id } });
  }
}
