import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Patch,
  Post,
  UseGuards,
} from '@nestjs/common';
import { BagService } from './bag.service';
import { JwtUserGuard } from 'src/auth/guard/jtw.user.guard';
import { GetUser } from 'src/auth/decorator/get.user.decorator';
import { User } from 'src/user/entity/user.entitty';
import { BagSerializer } from 'src/interceptor/bag.serializer';
import { FetchBagdto } from './dto/Fetch_bag.dto';
import { UpdateQuantityDto } from './dto/update_qunatity.dto';
import { CreateBagDto } from './dto/create_bag.dto';

@Controller('bag')
export class BagController {
  constructor(private readonly bagService: BagService) {}

  @Post()
  @UseGuards(JwtUserGuard)
  async createBag(@Body() bag: CreateBagDto, @GetUser() user: User) {
    return await this.bagService.createBag(bag, user);
  }
  @Get('/price')
  @UseGuards(JwtUserGuard)
  async fetchTotalPrice(@GetUser() user: User) {
    return await this.bagService.getTotalPrice(user);
  }

  @Get('/:page')
  @UseGuards(JwtUserGuard)
  @BagSerializer(FetchBagdto)
  async fetchBags(@Param('page') page: number, @GetUser() user: User) {
    return await this.bagService.fetchBags(page, user);
  }

  @Delete('/:id')
  @UseGuards(JwtUserGuard)
  async deleteBags(@Param('id') id: number) {
    return await this.bagService.deleteBags(id);
  }

  @Patch()
  @UseGuards(JwtUserGuard)
  async updateQunatity(@Body() bag: UpdateQuantityDto) {
    return await this.bagService.updateQuantity(bag);
  }
}
