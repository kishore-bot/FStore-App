import { Injectable } from "@nestjs/common";
import { AuthGuard } from "@nestjs/passport";

@Injectable()
export class JwtSellerGuard extends AuthGuard('seller-jwt') {
  constructor() {
    super();
  }
}