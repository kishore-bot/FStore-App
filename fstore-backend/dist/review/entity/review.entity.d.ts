import { Product } from 'src/product/entity/product.entity';
import { User } from 'src/user/entity/user.entitty';
export declare class Review {
    id: number;
    comment: string;
    rating: number;
    createdAt: Date;
    product: Product;
    user: User;
}
