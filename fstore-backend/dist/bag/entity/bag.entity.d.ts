import { Product } from 'src/product/entity/product.entity';
import { User } from 'src/user/entity/user.entitty';
export declare class Bag {
    id: number;
    quantity: number;
    price: number;
    color: string;
    size: string;
    product: Product;
    user: User;
    createdAt: Date;
}
