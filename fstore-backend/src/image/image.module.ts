import { Module } from '@nestjs/common';
import { ImageService } from './image.service';
import { ImageController } from './image.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ProductImages } from './entity/product_image.entity';
import { ProductModule } from 'src/product/product.module';
import { AwsModule } from 'src/aws/aws.module';

@Module({
  imports: [TypeOrmModule.forFeature([ProductImages]),ProductModule,AwsModule],
  providers: [ImageService],
  controllers: [ImageController]
})
export class ImageModule {}
