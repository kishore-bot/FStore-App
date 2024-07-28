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
exports.ProductController = void 0;
const common_1 = require("@nestjs/common");
const product_service_1 = require("./product.service");
const create_product_dto_1 = require("./dto/create_product.dto");
const jwt_seller_guard_1 = require("../auth/guard/jwt.seller.guard");
const update_product_dto_1 = require("./dto/update_product.dto");
const jtw_user_guard_1 = require("../auth/guard/jtw.user.guard");
const serialize_interceptor_1 = require("../interceptor/serialize.interceptor");
const fetch_product_by_id_dto_1 = require("./dto/fetch_product_by_id.dto");
const product_query_1 = require("./dto/product_query");
const Fetch_product_dto_1 = require("./dto/Fetch_product.dto");
const fetch_products_interceptot_1 = require("../interceptor/fetch_products.interceptot");
const get_seller_decorator_1 = require("../auth/decorator/get.seller.decorator");
const seller_entitty_1 = require("../seller/entity/seller.entitty");
const get_user_decorator_1 = require("../auth/decorator/get.user.decorator");
const user_entitty_1 = require("../user/entity/user.entitty");
const brands_dto_1 = require("./dto/brands.dto");
const sub_query_dto_1 = require("./dto/sub_query.dto");
let ProductController = class ProductController {
    constructor(productService) {
        this.productService = productService;
    }
    async create(product, seller) {
        return await this.productService.create(product, seller);
    }
    async update(id, product, seller) {
        return await this.productService.update(id, product, seller);
    }
    async fetchBrands(mainCategory) {
        return await this.productService.fetchBrands(mainCategory);
    }
    async fetchCategory(query) {
        return await this.productService.fetchCategories(query);
    }
    async fetchByQuery(query, user) {
        const product = await this.productService.fetchByQuery(query, user);
        return product;
    }
    async fetchProductDetails(productId, user) {
        return await this.productService.fetchProductDetails(productId, user);
    }
};
exports.ProductController = ProductController;
__decorate([
    (0, common_1.Post)('seller/create'),
    (0, common_1.UseGuards)(jwt_seller_guard_1.JwtSellerGuard),
    __param(0, (0, common_1.Body)()),
    __param(1, (0, get_seller_decorator_1.GetSeller)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [create_product_dto_1.CreateProductDto, seller_entitty_1.Seller]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "create", null);
__decorate([
    (0, common_1.Patch)('seller/update/:id'),
    (0, common_1.UseGuards)(jwt_seller_guard_1.JwtSellerGuard),
    __param(0, (0, common_1.Param)('id')),
    __param(1, (0, common_1.Body)()),
    __param(2, (0, get_seller_decorator_1.GetSeller)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Number, update_product_dto_1.UpdateProductDto,
        seller_entitty_1.Seller]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "update", null);
__decorate([
    (0, common_1.Get)('user/brands'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    (0, serialize_interceptor_1.Serialize)(brands_dto_1.BrandsDto),
    __param(0, (0, common_1.Query)('mainCategory')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "fetchBrands", null);
__decorate([
    (0, common_1.Get)('user/category'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, common_1.Query)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [sub_query_dto_1.SubQueryDto]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "fetchCategory", null);
__decorate([
    (0, common_1.Get)('user'),
    (0, fetch_products_interceptot_1.FetchproductSerializer)(Fetch_product_dto_1.FetchProductsDto),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, common_1.Query)()),
    __param(1, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [product_query_1.ProductQuery, user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "fetchByQuery", null);
__decorate([
    (0, common_1.Get)('/user/:id'),
    (0, serialize_interceptor_1.Serialize)(fetch_product_by_id_dto_1.FetchProductById),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, common_1.Param)('id')),
    __param(1, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Number, user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "fetchProductDetails", null);
exports.ProductController = ProductController = __decorate([
    (0, common_1.Controller)('product'),
    __metadata("design:paramtypes", [product_service_1.ProductService])
], ProductController);
//# sourceMappingURL=product.controller.js.map