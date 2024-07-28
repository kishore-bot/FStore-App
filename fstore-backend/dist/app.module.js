"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.AppModule = void 0;
const common_1 = require("@nestjs/common");
const auth_module_1 = require("./auth/auth.module");
const typeorm_1 = require("@nestjs/typeorm");
const user_entitty_1 = require("./user/entity/user.entitty");
const user_module_1 = require("./user/user.module");
const product_module_1 = require("./product/product.module");
const review_module_1 = require("./review/review.module");
const bag_module_1 = require("./bag/bag.module");
const order_module_1 = require("./order/order.module");
const favorite_module_1 = require("./favorite/favorite.module");
const seller_module_1 = require("./seller/seller.module");
const product_entity_1 = require("./product/entity/product.entity");
const config_1 = require("@nestjs/config");
const seller_entitty_1 = require("./seller/entity/seller.entitty");
const favourite_entity_1 = require("./favorite/entity/favourite.entity");
const bag_entity_1 = require("./bag/entity/bag.entity");
const size_entity_1 = require("./product/entity/size.entity");
const color_entity_1 = require("./product/entity/color.entity");
const adress_entity_1 = require("./user/entity/adress.entity");
const order_entity_1 = require("./order/entity/order.entity");
const product_order_enity_1 = require("./order/entity/product_order.enity");
const review_entity_1 = require("./review/entity/review.entity");
const aws_service_1 = require("./aws/aws.service");
const aws_module_1 = require("./aws/aws.module");
const product_image_entity_1 = require("./image/entity/product_image.entity");
const image_module_1 = require("./image/image.module");
let AppModule = class AppModule {
};
exports.AppModule = AppModule;
exports.AppModule = AppModule = __decorate([
    (0, common_1.Module)({
        imports: [
            config_1.ConfigModule.forRoot({ isGlobal: true }),
            auth_module_1.AuthModule,
            typeorm_1.TypeOrmModule.forRootAsync({
                inject: [config_1.ConfigService],
                useFactory: (config) => ({
                    type: 'postgres',
                    host: 'localhost',
                    port: 5432,
                    username: config.get('USER_NAME'),
                    password: config.get('PASSWORD'),
                    database: config.get('DB_NAME'),
                    entities: [
                        user_entitty_1.User,
                        seller_entitty_1.Seller,
                        product_entity_1.Product,
                        favourite_entity_1.Favorite,
                        bag_entity_1.Bag,
                        size_entity_1.Size,
                        color_entity_1.Color,
                        adress_entity_1.Address,
                        order_entity_1.Orders,
                        product_order_enity_1.ProductOrder,
                        review_entity_1.Review,
                        product_image_entity_1.ProductImages
                    ],
                    synchronize: true,
                }),
            }),
            user_module_1.UserModule,
            seller_module_1.SellerModule,
            product_module_1.ProductModule,
            review_module_1.ReviewModule,
            bag_module_1.BagModule,
            order_module_1.OrderModule,
            favorite_module_1.FavoriteModule,
            aws_module_1.AwsModule,
            image_module_1.ImageModule,
        ],
        controllers: [],
        providers: [aws_service_1.AwsService],
    })
], AppModule);
//# sourceMappingURL=app.module.js.map