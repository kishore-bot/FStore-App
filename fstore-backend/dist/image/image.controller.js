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
exports.ImageController = void 0;
const common_1 = require("@nestjs/common");
const jwt_seller_guard_1 = require("../auth/guard/jwt.seller.guard");
const image_service_1 = require("./image.service");
const product_image_dto_1 = require("./dto/product_image.dto");
let ImageController = class ImageController {
    constructor(imageService) {
        this.imageService = imageService;
    }
    async getProductImageUrl(body) {
        const urls = await this.imageService.createProductImage(body);
        return { urls };
    }
};
exports.ImageController = ImageController;
__decorate([
    (0, common_1.Post)('product'),
    (0, common_1.UseGuards)(jwt_seller_guard_1.JwtSellerGuard),
    __param(0, (0, common_1.Body)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [product_image_dto_1.ProductImagesDto]),
    __metadata("design:returntype", Promise)
], ImageController.prototype, "getProductImageUrl", null);
exports.ImageController = ImageController = __decorate([
    (0, common_1.Controller)('image'),
    __metadata("design:paramtypes", [image_service_1.ImageService])
], ImageController);
//# sourceMappingURL=image.controller.js.map