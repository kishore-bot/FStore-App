import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  UseGuards,
} from '@nestjs/common';
import { UserService } from './user.service';
import { CreateAddressDto } from './dto/create_adress.dto';
import { JwtUserGuard } from 'src/auth/guard/jtw.user.guard';
import { GetUser } from 'src/auth/decorator/get.user.decorator';
import { User } from './entity/user.entitty';
import { Serialize } from 'src/interceptor/serialize.interceptor';
import { ProfileDto } from './dto/profile.dto';

@Controller('user')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Post('/address/change/:id')
  @UseGuards(JwtUserGuard)
  async changeAddress(@Param('id') id: number, @GetUser() user: User) {
    return await this.userService.changePrimaryAddress(id, user);
  }

  @Get('/address/primary')
  @UseGuards(JwtUserGuard)
  async fetchPrimaryAddress(@GetUser() user: User) {
    return await this.userService.fetchPrimaryAddress(user);
  }

  @Post('/address')
  @UseGuards(JwtUserGuard)
  async createAddress(
    @Body() createAddressDto: CreateAddressDto,
    @GetUser() user: User,
  ) {
    return this.userService.createAddress(createAddressDto, user);
  }

  @Get('/address')
  @UseGuards(JwtUserGuard)
  async getAddress(@GetUser() user: User) {
    return await this.userService.fetchAddress(user);
  }

  @Delete('/address/:id')
  @UseGuards(JwtUserGuard)
  async deleteAddress(@Param('id') id: number, @GetUser() user: User) {
    return await this.userService.deleteAddress(user, id);
  }

  @Get('/me')
  @UseGuards(JwtUserGuard)
  @Serialize(ProfileDto)
  async profile(@GetUser() user: User) {
    return user;
  }
}
