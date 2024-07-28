import { Optional } from '@nestjs/common';
import { Expose, Transform } from 'class-transformer';

export class FetchFavProductsDto {
  @Expose()
  @Optional()
  @Transform(({ obj }) => {
    
    return obj.product.seller.name;
  })
  name: string;

  @Expose()
  @Optional()
  @Transform(({ obj }) => {
    return obj.size;
  })
  size: string;

  @Expose()
  @Optional()
  @Transform(({ obj }) => {
    return obj.color;
  })
  color: string;

  @Expose()
  @Optional()
  @Transform(({ obj }) => {
    return obj.product.category;
  })
  category: string;

  @Expose()
  @Optional()
  @Transform(({ obj }) => {
    return obj.product.availability > 0;
  })
  available: string;

  @Expose()
  @Transform(({ obj }) => {
    const oneMonthInMilliseconds = 30 * 24 * 60 * 60 * 1000;
    const isWithinOneMonth =
      Date.now() - new Date(obj.product.createdAt).getTime() <=
      oneMonthInMilliseconds;
    return isWithinOneMonth;
  })
  new: boolean;

  @Expose()
  @Transform(({ obj }) => {
    return obj.product.discount;
  })
  discount: number;

  @Expose()
  @Transform(({ obj }) => {
    return obj.product.rating;
  })
  rating: number;

  @Expose()
  @Transform(({ obj }) => {
    return obj.product.noOfRating;
  })
  noOfRating: number;

  @Expose()
  @Transform(({ obj }) => {
    return obj.product.popularity;
  })
  popularity: number;

  @Expose()
  @Transform(({ obj }) => {
    return obj.product.id;
  })
  id: number;

  @Expose()
  @Transform(({ obj }) => {
    if (obj.product.discount !== 0) {
      return Math.round(
        obj.product.price - obj.product.price * (obj.product.discount / 100),
      );
    } else return 0;
  })
  dPrice: number;

  @Expose()
  @Transform(({ obj }) => {
    return Math.round(obj.product.price);
  })
  price: number;

  @Expose()
  @Transform(({ obj }) => {
    return obj.product.thumbnailUrl
  })
  thumbnailUrl: number;
}
