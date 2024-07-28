import { Bag } from './entity/bag.entity';
import { Repository } from 'typeorm';
import { User } from 'src/user/entity/user.entitty';
import { ProductService } from 'src/product/product.service';
import { CreateBagDto } from './dto/create_bag.dto';
import { UpdateQuantityDto } from './dto/update_qunatity.dto';
import { AwsService } from 'src/aws/aws.service';
export declare class BagService {
    private readonly bagRepo;
    private readonly productService;
    private readonly awsService;
    constructor(bagRepo: Repository<Bag>, productService: ProductService, awsService: AwsService);
    createBag(bagDto: CreateBagDto, user: User): Promise<{
        message: string;
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
                bagItems: Bag[];
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
    getTotalPrice(user: User): Promise<{
        price: number;
    }>;
    deleteBags(uid: number): Promise<{
        message: string;
    }>;
    updateQuantity(quantity: UpdateQuantityDto): Promise<{
        message: string;
    }>;
    fetchBagsForOrders(user: User): Promise<Bag[]>;
    clearBagsForOrders(user: User): Promise<void>;
}
