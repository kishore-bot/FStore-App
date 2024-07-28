"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.FavoriteModule = void 0;
const common_1 = require("@nestjs/common");
const typeorm_1 = require("@nestjs/typeorm");
const favorite_controller_1 = require("./favorite.controller");
const favorite_service_1 = require("./favorite.service");
const favourite_entity_1 = require("./entity/favourite.entity");
const product_module_1 = require("../product/product.module");
const aws_module_1 = require("../aws/aws.module");
let FavoriteModule = class FavoriteModule {
};
exports.FavoriteModule = FavoriteModule;
exports.FavoriteModule = FavoriteModule = __decorate([
    (0, common_1.Module)({
        imports: [
            typeorm_1.TypeOrmModule.forFeature([favourite_entity_1.Favorite]),
            (0, common_1.forwardRef)(() => product_module_1.ProductModule),
            aws_module_1.AwsModule
        ],
        controllers: [favorite_controller_1.FavoriteController],
        providers: [favorite_service_1.FavoriteService],
        exports: [favorite_service_1.FavoriteService],
    })
], FavoriteModule);
//# sourceMappingURL=favorite.module.js.map