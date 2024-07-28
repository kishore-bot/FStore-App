import { Orders } from './entity/order.entity';
import { Repository } from 'typeorm';
import { ProductOrder } from './entity/product_order.enity';
import { BagService } from 'src/bag/bag.service';
import { User } from 'src/user/entity/user.entitty';
import { UserService } from 'src/user/user.service';
import { ProductService } from 'src/product/product.service';
export declare class OrderService {
    private readonly orderRepo;
    private readonly productOrderRepo;
    private readonly bagsService;
    private readonly userService;
    private readonly productService;
    constructor(orderRepo: Repository<Orders>, productOrderRepo: Repository<ProductOrder>, bagsService: BagService, userService: UserService, productService: ProductService);
    generateOrderId(): string;
    generateRandomString(length?: number): string;
    placeOrders(user: User): Promise<{
        message: string;
    }>;
    fetchOrders(user: User): Promise<Orders[]>;
    fetchOrderDetails(): Promise<void>;
}
