import { Orders } from './entity/order.entity';
import { Repository } from 'typeorm';
import { ProductOrder } from './entity/product_order.enity';
import { BagService } from 'src/bag/bag.service';
import { User } from 'src/user/entity/user.entitty';
import { UserService } from 'src/user/user.service';
import { ProductService } from 'src/product/product.service';
import { AwsService } from 'src/aws/aws.service';
export declare class OrderService {
    private readonly orderRepo;
    private readonly productOrderRepo;
    private readonly bagsService;
    private readonly userService;
    private readonly productService;
    private readonly awsService;
    constructor(orderRepo: Repository<Orders>, productOrderRepo: Repository<ProductOrder>, bagsService: BagService, userService: UserService, productService: ProductService, awsService: AwsService);
    generateOrderId(): string;
    generateRandomString(length?: number): string;
    placeOrders(user: User): Promise<{
        message: string;
    }>;
    fetchOrders(user: User, page?: number): Promise<{
        total: number;
        orders: Orders[];
    }>;
    fetchOrderDetails(user: User, orderId: number): Promise<{
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
                productOrders: ProductOrder[];
                reviews: import("../review/entity/review.entity").Review[];
                images: import("../image/entity/product_image.entity").ProductImages[];
            };
            id: number;
            quantity: number;
            price: number;
            color: string;
            size: string;
            createdAt: Date;
            order: Orders;
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
    isProductOrdered(productId: number, user: User): Promise<{
        isOrdered: boolean;
    }>;
}
