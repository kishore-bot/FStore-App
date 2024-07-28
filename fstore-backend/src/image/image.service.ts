import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { AwsService } from 'src/aws/aws.service';
import { ProductImages } from './entity/product_image.entity';
import { Repository } from 'typeorm';
import { ProductService } from 'src/product/product.service';
import { ProductImagesDto } from './dto/product_image.dto';

@Injectable()
export class ImageService {
  constructor(
    private readonly awsService: AwsService,
    @InjectRepository(ProductImages)
    private readonly productImagesRepository: Repository<ProductImages>,
    private readonly productService: ProductService,
  ) {}

  async createProductImage(body: ProductImagesDto): Promise<string[]> {
    let images: string[] = [];
    const date = Date.now();
    const product = await this.productService.forProductImages(
      body.id,
      date.toString(),
    );
    for (let i = 1; i <= body.noOfImages; i++) {
      const key = `product/${product.id.toString()}/m${i}_${date}`;
      const url = await this.awsService.putObjectUrl(key);
      images.push(url);
      const image = this.productImagesRepository.create({
        key: key,
        product: product,
      });
      await this.productImagesRepository.save(image);
    }
    return images;
  }
}
