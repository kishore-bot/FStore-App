import {  Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Favorite } from './entity/favourite.entity';
import { ProductService } from 'src/product/product.service';
import { User } from 'src/user/entity/user.entitty';
import { CreateFavDto } from './dto/create_fav_dto';
import { AwsService } from 'src/aws/aws.service';

@Injectable()
export class FavoriteService {
  constructor(
    private productService: ProductService,
    @InjectRepository(Favorite) private readonly favRepo: Repository<Favorite>,
    private readonly awsService: AwsService,
  ) {}

  async addToFavorite(favDto: CreateFavDto, user: User) {
    const existingFavorite = await this.favRepo.findOne({
      where: {
        product: { id: favDto.id },
        user: { id: user.id },
      },
    });
    if (existingFavorite) {
      return { message: 'All ready this Product in Favourites' };
    }
    const product = await this.productService.fetchProduct(favDto.id);
    const fav = this.favRepo.create({
      color: favDto.color,
      size: favDto.size,
      user: user,
      product: product,
    });
    await this.favRepo.save(fav);
    return { message: 'Success.. Added to Favorites' };
  }

  async getAllFavProducts(
    userInfo: User,
    page: number = 1,
    limit: number = 5,
  ): Promise<{ favorites: Favorite[]; total: number }> {
    const skip = (page - 1) * limit;

    const [favorites, total] = await this.favRepo
      .createQueryBuilder('favorite')
      .leftJoinAndSelect('favorite.product', 'product')
      .leftJoinAndSelect('product.seller', 'seller')
      .where('favorite.user = :userId', { userId: userInfo.id })
      .skip(skip)
      .take(limit)
      .getManyAndCount();

    const processedFavorites = await Promise.all(
      favorites.map(async (favorite) => {
        const product = favorite.product;
        const thumbnailUrl = product.thumbnail
          ? await this.awsService.getObjectUrl(product.thumbnail)
          : null;

        return {
          ...favorite,
          product: {
            ...product,
            thumbnailUrl,
          },
        };
      }),
    );

    return { total, favorites: processedFavorites };
  }

  // async getAllFavProducts(
  //   userInfo: User,
  //   page: number = 1,
  //   limit: number = 5,
  // ): Promise<{ favorites: Favorite[]; total: number }> {
  //   const skip = (page - 1) * limit;

  //   const [favorites, total] = await this.favRepo
  //     .createQueryBuilder('favorite')
  //     .leftJoinAndSelect('favorite.product', 'product')
  //     .leftJoinAndSelect('product.seller', 'seller')
  //     .where('favorite.user = :userId', { userId: userInfo.id })
  //     .skip(skip)
  //     .take(limit)
  //     .getManyAndCount();
  //   return { total, favorites };
  // }

  async deleteFavProduct(productId: number, user: User) {
    const existingFavorite = await this.favRepo.findOne({
      where: {
        product: { id: productId },
        user: { id: user.id },
      },
    });
    if (!existingFavorite) {
      return { message: 'Product not fount' };
    }
    await this.favRepo.remove(existingFavorite);
    return { message: 'Removed SuccessFully' };
  }
}
