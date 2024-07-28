import { Orders } from './order.entity';
import { Product } from 'src/product/entity/product.entity';
export declare class ProductOrder {
    id: number;
    quantity: number;
    price: number;
    color: string;
    size: string;
    createdAt: Date;
    order: Orders;
    product: Product;
}
