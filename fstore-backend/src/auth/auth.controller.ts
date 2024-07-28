import { Body, Controller, Post } from '@nestjs/common';
import { AuthService } from './auth.service';
import { CreateUser } from './dtos/create-user.dto';
import { CreateSeller } from './dtos/create.seller.dto';

@Controller('auth')
export class AuthController {
  constructor(private readonly authSer: AuthService) {}

  @Post('/register/user')
  registerUser(@Body() user: CreateUser) {
    return this.authSer.registerUser(user);
  }

  @Post('/login/user')
  loginUser(@Body() user: CreateUser) {
    return this.authSer.validateUser(user);
  }

  @Post('/register/seller')
  registerSeller(@Body() seller: CreateSeller) {
    return this.authSer.registerSeller(seller);
  }

  @Post('/login/seller')
  loginSeller(@Body() user: CreateSeller) {
    return this.authSer.validateSeller(user);
  }
}
