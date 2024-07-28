import { AuthService } from './auth.service';
import { CreateUser } from './dtos/create-user.dto';
import { CreateSeller } from './dtos/create.seller.dto';
export declare class AuthController {
    private readonly authSer;
    constructor(authSer: AuthService);
    registerUser(user: CreateUser): Promise<{
        token: string;
    }>;
    loginUser(user: CreateUser): Promise<{
        token: string;
    }>;
    registerSeller(seller: CreateSeller): Promise<{
        token: string;
    }>;
    loginSeller(user: CreateSeller): Promise<{
        token: string;
    }>;
}
