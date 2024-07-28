import { Bag } from 'src/bag/entity/bag.entity';
import { Orders } from 'src/order/entity/order.entity';
import { Favorite } from 'src/favorite/entity/favourite.entity';
import { Address } from './adress.entity';
import { Review } from 'src/review/entity/review.entity';
export declare class User {
    id: number;
    name: string;
    email: string;
    password: string;
    primaryAddress: number;
    favorites: Favorite[];
    bags: Bag[];
    addresses: Address[];
    orders: Orders[];
    reviews: Review[];
}
