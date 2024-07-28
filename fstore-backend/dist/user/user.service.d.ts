import { Repository } from 'typeorm';
import { User } from './entity/user.entitty';
import { Address } from './entity/adress.entity';
import { CreateAddressDto } from './dto/create_adress.dto';
export declare class UserService {
    private readonly userRepo;
    private readonly addressRepo;
    constructor(userRepo: Repository<User>, addressRepo: Repository<Address>);
    createAddress(createAddressDto: CreateAddressDto, user: User): Promise<{
        message: string;
    }>;
    fetchAddress(user: User): Promise<{
        addresses: {
            id: number;
            name: string;
            address: string;
            city: string;
            state: string;
            pincode: number;
            country: string;
        }[];
        primaryAddress: number;
    }>;
    deleteAddress(user: User, addressId: number): Promise<{
        message: string;
    }>;
    fetchPrimaryAddress(user: User): Promise<Address | {
        address: any;
        city: any;
        country: any;
        id: any;
        name: any;
        pincode: any;
        state: any;
    }>;
    changePrimaryAddress(adddressId: number, user: User): Promise<{
        message: string;
    }>;
}
