import { ConfigService } from '@nestjs/config';
import { Strategy } from 'passport-jwt';
import { AuthService } from '../auth.service';
declare const JwtUserStrategy_base: new (...args: any[]) => InstanceType<typeof Strategy>;
export declare class JwtUserStrategy extends JwtUserStrategy_base {
    private readonly configService;
    private readonly authervice;
    constructor(configService: ConfigService, authervice: AuthService);
    validate(payload: {
        id: string;
        email: string;
    }): Promise<import("../../user/entity/user.entitty").User>;
}
export {};
