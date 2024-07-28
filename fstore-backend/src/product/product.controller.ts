import {
  Body,
  Controller,
  Get,
  Param,
  Patch,
  Post,
  Query,
  UseGuards,
} from '@nestjs/common';
import { ProductService } from './product.service';
import { CreateProductDto } from './dto/create_product.dto';
import { JwtSellerGuard } from 'src/auth/guard/jwt.seller.guard';
import { UpdateProductDto } from './dto/update_product.dto';
import { JwtUserGuard } from 'src/auth/guard/jtw.user.guard';
import { Serialize } from 'src/interceptor/serialize.interceptor';
import { FetchProductById } from './dto/fetch_product_by_id.dto';
import { ProductQuery } from './dto/product_query';
import { FetchProductsDto } from './dto/Fetch_product.dto';
import { FetchproductSerializer } from 'src/interceptor/fetch_products.interceptot';
import { GetSeller } from 'src/auth/decorator/get.seller.decorator';
import { Seller } from 'src/seller/entity/seller.entitty';
import { GetUser } from 'src/auth/decorator/get.user.decorator';
import { User } from 'src/user/entity/user.entitty';
import { BrandsDto } from './dto/brands.dto';
import { SubQueryDto } from './dto/sub_query.dto';

@Controller('product')
export class ProductController {
  constructor(private readonly productService: ProductService) {}

  @Post('seller/create')
  @UseGuards(JwtSellerGuard)
  async create(@Body() product: CreateProductDto, @GetSeller() seller: Seller) {
    return await this.productService.create(product, seller);
  }

  @Patch('seller/update/:id')
  @UseGuards(JwtSellerGuard)
  async update(
    @Param('id') id: number,
    @Body() product: UpdateProductDto,
    @GetSeller() seller: Seller,
  ) {
    return await this.productService.update(id, product, seller);
  }

  @Get('user/brands')
  @UseGuards(JwtUserGuard)
  @Serialize(BrandsDto)
  async fetchBrands(@Query('mainCategory') mainCategory: string) {
    return await this.productService.fetchBrands(mainCategory);
  }

  @Get('user/category')
  @UseGuards(JwtUserGuard)
  async fetchCategory(@Query() query: SubQueryDto) {
    return await this.productService.fetchCategories(query);
  }

  @Get('user')
  @FetchproductSerializer(FetchProductsDto)
  @UseGuards(JwtUserGuard)
  async fetchByQuery(@Query() query: ProductQuery, @GetUser() user: User) {
    const product = await this.productService.fetchByQuery(query, user);
    return product;
  }

  @Get('/user/:id')
  @Serialize(FetchProductById)
  @UseGuards(JwtUserGuard)
  async fetchProductDetails(
    @Param('id') productId: number,
    @GetUser() user: User,
  ) {
    return await this.productService.fetchProductDetails(productId, user);
  }
}
