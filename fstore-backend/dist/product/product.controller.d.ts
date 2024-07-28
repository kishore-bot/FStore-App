import { ProductService } from './product.service';
import { CreateProductDto } from './dto/create_product.dto';
import { UpdateProductDto } from './dto/update_product.dto';
import { ProductQuery } from './dto/product_query';
import { Seller } from 'src/seller/entity/seller.entitty';
import { User } from 'src/user/entity/user.entitty';
import { SubQueryDto } from './dto/sub_query.dto';
export declare class ProductController {
    private readonly productService;
    constructor(productService: ProductService);
    create(product: CreateProductDto, seller: Seller): Promise<import("./entity/product.entity").Product>;
    update(id: number, product: UpdateProductDto, seller: Seller): Promise<import("./entity/product.entity").Product>;
    fetchBrands(mainCategory: string): Promise<Seller[]>;
    fetchCategory(query: SubQueryDto): Promise<any[]>;
    fetchByQuery(query: ProductQuery, user: User): Promise<{
        total: number;
        products: {
            isFavorite: boolean;
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
            colors: import("./entity/color.entity").Color[];
            sizes: import("./entity/size.entity").Size[];
            seller: Seller;
            favorites: import("../favorite/entity/favourite.entity").Favorite[];
            bagItems: import("../bag/entity/bag.entity").Bag[];
            productOrders: import("../order/entity/product_order.enity").ProductOrder[];
            reviews: import("../review/entity/review.entity").Review[];
            images: import("../image/entity/product_image.entity").ProductImages[];
        }[];
    }>;
    fetchProductDetails(productId: number, user: User): Promise<import("./entity/product.entity").Product>;
}
