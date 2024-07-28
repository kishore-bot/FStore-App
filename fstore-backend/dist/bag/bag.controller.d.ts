import { BagService } from './bag.service';
import { User } from 'src/user/entity/user.entitty';
import { UpdateQuantityDto } from './dto/update_qunatity.dto';
import { CreateBagDto } from './dto/create_bag.dto';
export declare class BagController {
    private readonly bagService;
    constructor(bagService: BagService);
    createBag(bag: CreateBagDto, user: User): Promise<{
        message: string;
    }>;
    fetchTotalPrice(user: User): Promise<{
        price: number;
    }>;
    fetchBags(page: number, user: User): Promise<{
        bags: {
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
                bagItems: import("./entity/bag.entity").Bag[];
                productOrders: import("../order/entity/product_order.enity").ProductOrder[];
                reviews: import("../review/entity/review.entity").Review[];
                images: import("../image/entity/product_image.entity").ProductImages[];
            };
            id: number;
            quantity: number;
            price: number;
            color: string;
            size: string;
            user: User;
            createdAt: Date;
        }[];
        total: number;
    }>;
    deleteBags(id: number): Promise<{
        message: string;
    }>;
    updateQunatity(bag: UpdateQuantityDto): Promise<{
        message: string;
    }>;
}
