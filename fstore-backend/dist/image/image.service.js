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
exports.ImageService = void 0;
const common_1 = require("@nestjs/common");
const typeorm_1 = require("@nestjs/typeorm");
const aws_service_1 = require("../aws/aws.service");
const product_image_entity_1 = require("./entity/product_image.entity");
const typeorm_2 = require("typeorm");
const product_service_1 = require("../product/product.service");
let ImageService = class ImageService {
    constructor(awsService, productImagesRepository, productService) {
        this.awsService = awsService;
        this.productImagesRepository = productImagesRepository;
        this.productService = productService;
    }
    async createProductImage(body) {
        let images = [];
        const date = Date.now();
        const product = await this.productService.forProductImages(body.id, date.toString());
        for (let i = 1; i <= body.noOfImages; i++) {
            const key = `product/${product.id.toString()}/m${i}_${date}`;
            const url = await this.awsService.putObjectUrl(key);
            images.push(url);
            const image = this.productImagesRepository.create({
                key: key,
                product: product,
            });
            await this.productImagesRepository.save(image);
        }
        return images;
    }
};
exports.ImageService = ImageService;
exports.ImageService = ImageService = __decorate([
    (0, common_1.Injectable)(),
    __param(1, (0, typeorm_1.InjectRepository)(product_image_entity_1.ProductImages)),
    __metadata("design:paramtypes", [aws_service_1.AwsService,
        typeorm_2.Repository,
        product_service_1.ProductService])
], ImageService);
//# sourceMappingURL=image.service.js.map