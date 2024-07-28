import { Expose, Transform } from 'class-transformer';

export class FetchBagdto {

  @Expose()
  @Transform(({ obj }) => obj.id)
  id: number;

  @Expose()
  @Transform(({ obj }) => obj.size)
  size: string;

  @Expose()
  @Transform(({ obj }) => obj.color)
  color: string;

  @Expose()
  @Transform(({ obj }) => obj.product.category)
  category: string;

  @Expose()
  @Transform(({ obj }) => obj.product.availability > 0)
  available: boolean;

  @Expose()
  @Transform(({ obj }) => obj.product.id)
  productId: number;

  @Expose()
  @Transform(({ obj }) => Math.round(obj.price))
  price: number;

  @Expose()
  @Transform(({ obj }) => obj.quantity)
  quantity: number;

  @Expose()
  @Transform(({ obj }) => {
    return obj.product.thumbnailUrl
  })
  thumbnailUrl: number;
}
