"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.FavoriteService = void 0;
const common_1 = require("@nestjs/common");
const typeorm_1 = require("@nestjs/typeorm");
const typeorm_2 = require("typeorm");
const favourite_entity_1 = require("./entity/favourite.entity");
const product_service_1 = require("../product/product.service");
const aws_service_1 = require("../aws/aws.service");
let FavoriteService = class FavoriteService {
    constructor(productService, favRepo, awsService) {
        this.productService = productService;
        this.favRepo = favRepo;
        this.awsService = awsService;
    }
    async addToFavorite(favDto, user) {
        const existingFavorite = await this.favRepo.findOne({
            where: {
                product: { id: favDto.id },
                user: { id: user.id },
            },
        });
        if (existingFavorite) {
            return { message: 'All ready this Product in Favourites' };
        }
        const product = await this.productService.fetchProduct(favDto.id);
        const fav = this.favRepo.create({
            color: favDto.color,
            size: favDto.size,
            user: user,
            product: product,
        });
        await this.favRepo.save(fav);
        return { message: 'Success.. Added to Favorites' };
    }
    async getAllFavProducts(userInfo, page = 1, limit = 5) {
        const skip = (page - 1) * limit;
        const [favorites, total] = await this.favRepo
            .createQueryBuilder('favorite')
            .leftJoinAndSelect('favorite.product', 'product')
            .leftJoinAndSelect('product.seller', 'seller')
            .where('favorite.user = :userId', { userId: userInfo.id })
            .skip(skip)
            .take(limit)
            .getManyAndCount();
        const processedFavorites = await Promise.all(favorites.map(async (favorite) => {
            const product = favorite.product;
            const thumbnailUrl = product.thumbnail
                ? await this.awsService.getObjectUrl(product.thumbnail)
                : null;
            return {
                ...favorite,
                product: {
                    ...product,
                    thumbnailUrl,
                },
            };
        }));
        return { total, favorites: processedFavorites };
    }
    async deleteFavProduct(productId, user) {
        const existingFavorite = await this.favRepo.findOne({
            where: {
                product: { id: productId },
                user: { id: user.id },
            },
        });
        if (!existingFavorite) {
            return { message: 'Product not fount' };
        }
        await this.favRepo.remove(existingFavorite);
        return { message: 'Removed SuccessFully' };
    }
};
exports.FavoriteService = FavoriteService;
exports.FavoriteService = FavoriteService = __decorate([
    (0, common_1.Injectable)(),
    __param(1, (0, typeorm_1.InjectRepository)(favourite_entity_1.Favorite)),
    __metadata("design:paramtypes", [product_service_1.ProductService,
        typeorm_2.Repository,
        aws_service_1.AwsService])
], FavoriteService);
//# sourceMappingURL=favorite.service.js.map