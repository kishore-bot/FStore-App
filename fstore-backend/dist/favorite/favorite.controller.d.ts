import { FavoriteService } from './favorite.service';
import { User } from 'src/user/entity/user.entitty';
import { CreateFavDto } from './dto/create_fav_dto';
export declare class FavoriteController {
    private readonly favService;
    constructor(favService: FavoriteService);
    addToFav(fav: CreateFavDto, user: User): Promise<{
        message: string;
    }>;
    getAllFavProducts(user: User, page: number): Promise<{
        favorites: import("./entity/favourite.entity").Favorite[];
        total: number;
    }>;
    deleteFavProduct(id: number, user: User): Promise<{
        message: string;
    }>;
}
