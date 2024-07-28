import { OrderService } from './order.service';
import { User } from 'src/user/entity/user.entitty';
export declare class OrderController {
    private readonly orderService;
    constructor(orderService: OrderService);
    isOrdered(user: User, id: number): Promise<{
        isOrdered: boolean;
    }>;
    fetchById(user: User, id: number): Promise<{
        productOrders: {
            product: {
                thumbnailUrl: string;
                id: number;
                category: string;
                price: number;
                description: string;
                mainCategory: string;
                popularity: number;
                discount: number;
                availability: number;
                gender: string;
                rating: number;
                noOfRating: number;
                thumbnail: string;
                createdAt: Date;
                updatedAt: Date;
                colors: import("../product/entity/color.entity").Color[];
                sizes: import("../product/entity/size.entity").Size[];
                seller: import("../seller/entity/seller.entitty").Seller;
                favorites: import("../favorite/entity/favourite.entity").Favorite[];
                bagItems: import("../bag/entity/bag.entity").Bag[];
                productOrders: import("./entity/product_order.enity").ProductOrder[];
                reviews: import("../review/entity/review.entity").Review[];
                images: import("../image/entity/product_image.entity").ProductImages[];
            };
            id: number;
            quantity: number;
            price: number;
            color: string;
            size: string;
            createdAt: Date;
            order: import("./entity/order.entity").Orders;
        }[];
        id: number;
        orderNo: string;
        orderId: string;
        totalPrice: number;
        status: string;
        createdAt: Date;
        address: import("../user/entity/adress.entity").Address;
        user: User;
    }>;
    placeOrder(user: User): Promise<{
        message: string;
    }>;
    fetchOrder(user: User, page: number): Promise<{
        total: number;
        orders: import("./entity/order.entity").Orders[];
    }>;
}
