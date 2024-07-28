import { ConfigService } from '@nestjs/config';
import { Strategy } from 'passport-jwt';
import { AuthService } from '../auth.service';
declare const JwtSellerStrategy_base: new (...args: any[]) => InstanceType<typeof Strategy>;
export declare class JwtSellerStrategy extends JwtSellerStrategy_base {
    private readonly authervice;
    constructor(configService: ConfigService, authervice: AuthService);
    validate(payload: {
        id: string;
        email: string;
    }): Promise<import("../../seller/entity/seller.entitty").Seller>;
}
export {};
