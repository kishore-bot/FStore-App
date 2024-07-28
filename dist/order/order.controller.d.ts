import { OrderService } from './order.service';
import { User } from 'src/user/entity/user.entitty';
export declare class OrderController {
    private readonly orderService;
    constructor(orderService: OrderService);
    placeOrder(user: User): Promise<{
        message: string;
    }>;
    fetchOrder(user: User): Promise<import("./entity/order.entity").Orders[]>;
}
