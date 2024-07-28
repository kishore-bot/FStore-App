import { Body, Controller, Get, Post, UseGuards } from '@nestjs/common';
import { JwtSellerGuard } from 'src/auth/guard/jwt.seller.guard';
import { ImageService } from './image.service';
import { ProductImagesDto } from './dto/product_image.dto';

@Controller('image')
export class ImageController {
  constructor(private readonly imageService: ImageService) {}

  @Post('product')
  @UseGuards(JwtSellerGuard)
  async getProductImageUrl(@Body()body:ProductImagesDto) {
    const urls = await this.imageService.createProductImage(body);
    return { urls };
  }
}
