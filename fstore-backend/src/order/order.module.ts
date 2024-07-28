import { Module } from '@nestjs/common';
import { OrderController } from './order.controller';
import { OrderService } from './order.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Orders } from './entity/order.entity';
import { ProductOrder } from './entity/product_order.enity';
import { BagModule } from 'src/bag/bag.module';
import { UserModule } from 'src/user/user.module';
import { ProductModule } from 'src/product/product.module';
import { AwsModule } from 'src/aws/aws.module';

@Module({
  imports: [
    TypeOrmModule.forFeature([Orders, ProductOrder]),
    BagModule,
    UserModule,
    ProductModule,
    AwsModule
  ],
  controllers: [OrderController],
  providers: [OrderService],
})
export class OrderModule {}
