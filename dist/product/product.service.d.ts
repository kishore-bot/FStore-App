import { Product } from './entity/product.entity';
import { Repository } from 'typeorm';
import { ProductQuery } from './dto/product_query';
import { Seller } from 'src/seller/entity/seller.entitty';
import { User } from 'src/user/entity/user.entitty';
import { Color } from './entity/color.entity';
import { Size } from './entity/size.entity';
import { CreateProductDto } from './dto/create_product.dto';
import { ColorService } from './color/color.service';
import { SizeService } from './size/size.service';
import { SubQueryDto } from './dto/sub_query.dto';
export declare class ProductService {
    private readonly productRepo;
    private readonly colorService;
    private readonly sizeService;
    constructor(productRepo: Repository<Product>, colorService: ColorService, sizeService: SizeService);
    create(productDto: CreateProductDto, seller: Seller): Promise<Product>;
    update(id: number, productDto: Partial<Product>, seller: Seller): Promise<Product>;
    fetchCategories(query: SubQueryDto): Promise<any[]>;
    fetchBrands(mainCategory: string): Promise<Seller[]>;
    fetchProductDetails(productId: number, user: User): Promise<Product>;
    fetchByQuery(query: ProductQuery, user: User): Promise<{
        total: number;
        products: {
            isFavorite: boolean;
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
            createdAt: Date;
            updatedAt: Date;
            colors: Color[];
            sizes: Size[];
            seller: Seller;
            favorites: import("../favorite/entity/favourite.entity").Favorite[];
            bagItems: import("../bag/entity/bag.entity").Bag[];
            productOrders: import("../order/entity/product_order.enity").ProductOrder[];
        }[];
    }>;
    productForOrder(productId: number, quantityNo: number): Promise<{
        product: Product;
        quantityNo: number;
    }>;
    fetchProduct(productId: number): Promise<Product>;
}
