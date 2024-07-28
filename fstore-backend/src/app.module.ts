import { Module } from '@nestjs/common';
import { AuthModule } from './auth/auth.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from './user/entity/user.entitty';
import { UserModule } from './user/user.module';
import { ProductModule } from './product/product.module';
import { ReviewModule } from './review/review.module';
import { BagModule } from './bag/bag.module';
import { OrderModule } from './order/order.module';
import { FavoriteModule } from './favorite/favorite.module';
import { SellerModule } from './seller/seller.module';
import { Product } from './product/entity/product.entity';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { Seller } from './seller/entity/seller.entitty';
import { Favorite } from './favorite/entity/favourite.entity';
import { Bag } from './bag/entity/bag.entity';
import { Size } from './product/entity/size.entity';
import { Color } from './product/entity/color.entity';
import { Address } from './user/entity/adress.entity';
import { Orders } from './order/entity/order.entity';
import { ProductOrder } from './order/entity/product_order.enity';
import { Review } from './review/entity/review.entity';
import { AwsService } from './aws/aws.service';
import { AwsModule } from './aws/aws.module';
import { ProductImages } from './image/entity/product_image.entity';
import { ImageModule } from './image/image.module';

@Module({
  imports: [
    ConfigModule.forRoot({ isGlobal: true }),
    AuthModule,
    TypeOrmModule.forRootAsync({
      inject: [ConfigService],
      useFactory: (config: ConfigService) => ({
        type: 'postgres',
        host: 'localhost',
        port: 5432,
        username: config.get<string>('USER_NAME'),
        password: config.get<string>('PASSWORD'),
        database: config.get<string>('DB_NAME'),
        entities: [
          User,
          Seller,
          Product,
          Favorite,
          Bag,
          Size,
          Color,
          Address,
          Orders,
          ProductOrder,
          Review,
          ProductImages
        ],
        synchronize: true,
        // logging: true, // Enable logging to see detailed connection information
      }),
    }),
    UserModule,
    SellerModule,
    ProductModule,
    ReviewModule,
    BagModule,
    OrderModule,
    FavoriteModule,
    AwsModule,
    ImageModule,
  ],
  controllers: [],
  providers: [AwsService],
})
export class AppModule {}
