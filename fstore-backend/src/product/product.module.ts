import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ProductService } from './product.service';
import { ProductController } from './product.controller';
import { Product } from './entity/product.entity';
import { Color } from './entity/color.entity';
import { Size } from './entity/size.entity';
import { ColorService } from './color/color.service';
import { SizeService } from './size/size.service';
import { AwsModule } from 'src/aws/aws.module';

@Module({
  imports: [TypeOrmModule.forFeature([Product, Color, Size]),AwsModule],
  providers: [ProductService, ColorService, SizeService],
  controllers: [ProductController],
  exports: [ProductService],
})
export class ProductModule {}
