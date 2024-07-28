import { Repository } from 'typeorm';
import { Favorite } from './entity/favourite.entity';
import { ProductService } from 'src/product/product.service';
import { User } from 'src/user/entity/user.entitty';
import { CreateFavDto } from './dto/create_fav_dto';
import { AwsService } from 'src/aws/aws.service';
export declare class FavoriteService {
    private productService;
    private readonly favRepo;
    private readonly awsService;
    constructor(productService: ProductService, favRepo: Repository<Favorite>, awsService: AwsService);
    addToFavorite(favDto: CreateFavDto, user: User): Promise<{
        message: string;
    }>;
    getAllFavProducts(userInfo: User, page?: number, limit?: number): Promise<{
        favorites: Favorite[];
        total: number;
    }>;
    deleteFavProduct(productId: number, user: User): Promise<{
        message: string;
    }>;
}
