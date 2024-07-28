import { Optional } from '@nestjs/common';
import { Expose, Transform } from 'class-transformer';

export class FetchProductsDto {
  @Expose()
  id: number;

  @Expose()
  price: number;

  @Expose()
  category: string;

  @Expose()
  @Optional()
  @Transform(({ obj }) => {
    if (obj.discount != 0) {
      const discountedPrice = obj.price - (obj.price * obj.discount) / 100;
      return Math.round(discountedPrice);
    }
    return 0;
  })
  dPrice: number;

  @Expose()
  rating: number;

  @Expose()
  noOfRating: number;

  @Expose()
  discount: number;

  @Expose()
  @Transform(({ obj }) => {
    const oneMonthInMilliseconds = 30 * 24 * 60 * 60 * 1000;
    const isWithinOneMonth =
      Date.now() - new Date(obj.createdAt).getTime() <= oneMonthInMilliseconds;
    return isWithinOneMonth;
  })
  new: boolean;

  @Expose()
  @Transform(({ obj }) => obj.seller.name)
  name: string;

  @Expose()
  @Transform(({ obj }) => obj.isFavorite)
  isFav: boolean;

  @Expose()
  @Transform(({obj})=>obj.thumbnailUrl)
  thumbnailUrl:string
}
