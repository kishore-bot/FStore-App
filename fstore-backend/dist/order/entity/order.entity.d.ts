import { Address } from 'src/user/entity/adress.entity';
import { ProductOrder } from './product_order.enity';
import { User } from 'src/user/entity/user.entitty';
export declare class Orders {
    id: number;
    orderNo: string;
    orderId: string;
    totalPrice: number;
    status: string;
    createdAt: Date;
    address: Address;
    productOrders: ProductOrder[];
    user: User;
}
