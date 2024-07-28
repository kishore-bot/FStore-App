import { Module } from '@nestjs/common';
import { AuthController } from './auth.controller';
import { AuthService } from './auth.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from 'src/user/entity/user.entitty';
import { JwtModule } from '@nestjs/jwt';
import { PassportModule } from '@nestjs/passport';
import { JwtUserStrategy } from './strategies/jwt.user.strategies';
import { ConfigService } from '@nestjs/config';
import { Seller } from 'src/seller/entity/seller.entitty';
import { JwtSellerStrategy } from './strategies/jwt.seller.startegies';

@Module({
  imports: [
    TypeOrmModule.forFeature([User,Seller]),
    JwtModule.registerAsync({
      inject: [ConfigService],
      useFactory:(config:ConfigService)=> {
        return {
          secret: config.getOrThrow('JWT_SECRET')
        }
      },
    }),
    PassportModule,
    JwtModule.register({}),
  ],
  controllers: [AuthController],
  providers: [AuthService, JwtUserStrategy,JwtSellerStrategy],
})
export class AuthModule {}
