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
exports.FavoriteController = void 0;
const common_1 = require("@nestjs/common");
const favorite_service_1 = require("./favorite.service");
const jtw_user_guard_1 = require("../auth/guard/jtw.user.guard");
const get_user_decorator_1 = require("../auth/decorator/get.user.decorator");
const user_entitty_1 = require("../user/entity/user.entitty");
const create_fav_dto_1 = require("./dto/create_fav_dto");
const fetch_fav_product_dto_1 = require("./dto/fetch_fav_product.dto");
const favourite_serialiser_1 = require("../interceptor/favourite.serialiser");
let FavoriteController = class FavoriteController {
    constructor(favService) {
        this.favService = favService;
    }
    async addToFav(fav, user) {
        return await this.favService.addToFavorite(fav, user);
    }
    getAllFavProducts(user, page) {
        return this.favService.getAllFavProducts(user, page);
    }
    deleteFavProduct(id, user) {
        return this.favService.deleteFavProduct(id, user);
    }
};
exports.FavoriteController = FavoriteController;
__decorate([
    (0, common_1.Post)(),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, common_1.Body)()),
    __param(1, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [create_fav_dto_1.CreateFavDto, user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], FavoriteController.prototype, "addToFav", null);
__decorate([
    (0, common_1.Get)('/:page'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    (0, favourite_serialiser_1.FavoriteSerializer)(fetch_fav_product_dto_1.FetchFavProductsDto),
    __param(0, (0, get_user_decorator_1.GetUser)()),
    __param(1, (0, common_1.Param)('page')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [user_entitty_1.User, Number]),
    __metadata("design:returntype", void 0)
], FavoriteController.prototype, "getAllFavProducts", null);
__decorate([
    (0, common_1.Delete)('/:id'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, common_1.Param)('id')),
    __param(1, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Number, user_entitty_1.User]),
    __metadata("design:returntype", void 0)
], FavoriteController.prototype, "deleteFavProduct", null);
exports.FavoriteController = FavoriteController = __decorate([
    (0, common_1.Controller)('favorite'),
    __metadata("design:paramtypes", [favorite_service_1.FavoriteService])
], FavoriteController);
//# sourceMappingURL=favorite.controller.js.map