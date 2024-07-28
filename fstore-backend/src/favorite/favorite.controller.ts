import { Body, Controller, Delete, Get, Param, Post, UseGuards } from '@nestjs/common';
import { FavoriteService } from './favorite.service';
import { JwtUserGuard } from 'src/auth/guard/jtw.user.guard';
import { GetUser } from 'src/auth/decorator/get.user.decorator';
import { User } from 'src/user/entity/user.entitty';
import { CreateFavDto } from './dto/create_fav_dto';
import { FetchFavProductsDto } from './dto/fetch_fav_product.dto';
import { FavoriteSerializer } from 'src/interceptor/favourite.serialiser';

@Controller('favorite')
export class FavoriteController {
  constructor(private readonly favService: FavoriteService) {}

  @Post()
  @UseGuards(JwtUserGuard)
  async addToFav(@Body() fav: CreateFavDto, @GetUser() user: User) {
    return await this.favService.addToFavorite(fav, user);
  }

  @Get('/:page')
  @UseGuards(JwtUserGuard)
  @FavoriteSerializer(FetchFavProductsDto)
  getAllFavProducts(@GetUser() user: User, @Param('page') page: number) {
    return this.favService.getAllFavProducts(user,page);
  }

  @Delete('/:id')
  @UseGuards(JwtUserGuard)
  deleteFavProduct(@Param('id') id: number, @GetUser() user: User){
    return this.favService.deleteFavProduct(id,user);
  }
}
