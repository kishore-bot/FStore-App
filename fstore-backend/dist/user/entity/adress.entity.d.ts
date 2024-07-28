import { User } from './user.entitty';
import { Orders } from 'src/order/entity/order.entity';
export declare class Address {
    id: number;
    name: string;
    address: string;
    city: string;
    state: string;
    pincode: number;
    country: string;
    user: User;
    orders: Orders[];
}
