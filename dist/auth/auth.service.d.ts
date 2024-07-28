import { User } from 'src/user/entity/user.entitty';
import { Repository } from 'typeorm';
import { CreateUser } from './dtos/create-user.dto';
import { JwtService } from '@nestjs/jwt';
import { Seller } from 'src/seller/entity/seller.entitty';
import { CreateSeller } from './dtos/create.seller.dto';
export declare class AuthService {
    private readonly userRepo;
    private readonly sellerRepo;
    private readonly jwtService;
    constructor(userRepo: Repository<User>, sellerRepo: Repository<Seller>, jwtService: JwtService);
    registerUser(attr: CreateUser): Promise<{
        token: string;
    }>;
    registerSeller(attr: CreateSeller): Promise<{
        token: string;
    }>;
    validateUser(attr: CreateUser): Promise<{
        token: string;
    }>;
    validateSeller(attr: CreateSeller): Promise<{
        token: string;
    }>;
    passwordHasher(pass: string): Promise<string>;
    verifyUser(id: string): Promise<User | null>;
    verifySeller(id: string): Promise<Seller>;
}
