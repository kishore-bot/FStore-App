import { UserService } from './user.service';
import { CreateAddressDto } from './dto/create_adress.dto';
import { User } from './entity/user.entitty';
export declare class UserController {
    private readonly userService;
    constructor(userService: UserService);
    changeAddress(id: number, user: User): Promise<{
        message: string;
    }>;
    fetchPrimaryAddress(user: User): Promise<import("./entity/adress.entity").Address | {
        address: any;
        city: any;
        country: any;
        id: any;
        name: any;
        pincode: any;
        state: any;
    }>;
    createAddress(createAddressDto: CreateAddressDto, user: User): Promise<{
        message: string;
    }>;
    getAddress(user: User): Promise<{
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
    deleteAddress(id: number, user: User): Promise<{
        message: string;
    }>;
}
