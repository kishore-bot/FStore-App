import { Product } from 'src/product/entity/product.entity';
export declare class Seller {
    id: number;
    name: string;
    email: string;
    password: string;
    joinedAt: Date;
    products: Product[];
}
