import { Expose, Transform } from 'class-transformer';

export class OrdersDto {

  @Expose()
  @Transform(({ obj }) => obj.id)
  id: number;

  @Expose()
  @Transform(({ obj }) => obj.orderNo)
  orderNo: string;

  @Expose()
  @Transform(({ obj }) => obj.orderId)
  orderId: string;

  @Expose()
  @Transform(({ obj }) => obj.totalPrice)
  totalPrice: number;

  @Expose()
  @Transform(({ obj }) => obj.createdAt)
  createdAt: number;

  @Expose()
  @Transform(({ obj }) => obj.productOrdersCount)
  quantity: number;

}
