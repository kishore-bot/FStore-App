import { Expose, plainToClass, Transform, Type } from 'class-transformer';

export class OrderAddress {
  @Expose()
  @Transform(({ obj }) => {
    return (
      (obj.address || '') +
      ' ,' +
      (obj.city || '') +
      '\n' +
      (obj.state || '') +
      ' ,' +
      (obj.pincode || '') +
      ' ,' +
      (obj.country || '')
    ).trim();
  })
  address: string;
}

export class OrderProduct {
  @Expose()
  @Transform(({ obj }) => obj.product.seller.name)
  name: string;

  @Expose()
  @Transform(({ obj }) => obj.size)
  size: string;

  @Expose()
  @Transform(({ obj }) => obj.color)
  color: string;

  @Expose()
  @Transform(({ obj }) => obj.product?.category )
  category: string;

  @Expose()
  @Transform(({ obj }) => Math.round(obj.price))
  price: number;

  @Expose()
  @Transform(({ obj }) => obj.quantity)
  quantity: number;

  @Expose()
  @Transform(({ obj }) =>obj.product.thumbnailUrl)
  thumbnailUrl: string;
}

export class OrderByDto {
  @Expose()
  id: number;

  @Expose()
  orderNo: string;

  @Expose()
  orderId: string;

  @Expose()
  totalPrice: number;

  @Expose()
  createdAt: Date;

  @Expose()
  @Transform(({ obj }) => obj.productOrdersCount || 0)
  quantity: number;

  @Expose()
  @Type(() => OrderAddress)
  address: OrderAddress;

  @Expose()
  @Type(() => OrderProduct)
  @Transform(({ obj }) => {
    return obj.productOrders.map(item => plainToClass(OrderProduct, item, { excludeExtraneousValues: true }));
  })
  orderProducts: OrderProduct;
}
