import { Product } from 'src/product/entity/product.entity';
import { User } from 'src/user/entity/user.entitty';
export declare class Favorite {
    id: number;
    size: string;
    color: string;
    user: User;
    product: Product;
    createdAt: Date;
}
