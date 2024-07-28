import { Injectable } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { PassportStrategy } from '@nestjs/passport';
import { ExtractJwt, Strategy } from 'passport-jwt';

import { AuthService } from '../auth.service';

@Injectable()
export class JwtSellerStrategy extends PassportStrategy(Strategy, 'seller-jwt') {
  constructor(
    configService: ConfigService,
    private readonly authervice: AuthService,
  ) {
    super({
      jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
      ignoreExpiration: true,
      secretOrKey: configService.get('JWT_SECRET'),
    });
  }

  async validate(payload: { id: string; email: string }) {
    const admin = await this.authervice.verifySeller(payload.id);
    return admin;
  }
}