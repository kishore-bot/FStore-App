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
Object.defineProperty(exports, "__esModule", { value: true });
exports.FetchFavProductsDto = void 0;
const common_1 = require("@nestjs/common");
const class_transformer_1 = require("class-transformer");
class FetchFavProductsDto {
}
exports.FetchFavProductsDto = FetchFavProductsDto;
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, common_1.Optional)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.product.seller.name;
    }),
    __metadata("design:type", String)
], FetchFavProductsDto.prototype, "name", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, common_1.Optional)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.size;
    }),
    __metadata("design:type", String)
], FetchFavProductsDto.prototype, "size", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, common_1.Optional)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.color;
    }),
    __metadata("design:type", String)
], FetchFavProductsDto.prototype, "color", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, common_1.Optional)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.product.category;
    }),
    __metadata("design:type", String)
], FetchFavProductsDto.prototype, "category", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, common_1.Optional)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.product.availability > 0;
    }),
    __metadata("design:type", String)
], FetchFavProductsDto.prototype, "available", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        const oneMonthInMilliseconds = 30 * 24 * 60 * 60 * 1000;
        const isWithinOneMonth = Date.now() - new Date(obj.product.createdAt).getTime() <=
            oneMonthInMilliseconds;
        return isWithinOneMonth;
    }),
    __metadata("design:type", Boolean)
], FetchFavProductsDto.prototype, "new", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.product.discount;
    }),
    __metadata("design:type", Number)
], FetchFavProductsDto.prototype, "discount", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.product.rating;
    }),
    __metadata("design:type", Number)
], FetchFavProductsDto.prototype, "rating", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.product.noOfRating;
    }),
    __metadata("design:type", Number)
], FetchFavProductsDto.prototype, "noOfRating", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.product.popularity;
    }),
    __metadata("design:type", Number)
], FetchFavProductsDto.prototype, "popularity", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.product.id;
    }),
    __metadata("design:type", Number)
], FetchFavProductsDto.prototype, "id", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        if (obj.product.discount !== 0) {
            return Math.round(obj.product.price - obj.product.price * (obj.product.discount / 100));
        }
        else
            return 0;
    }),
    __metadata("design:type", Number)
], FetchFavProductsDto.prototype, "dPrice", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return Math.round(obj.product.price);
    }),
    __metadata("design:type", Number)
], FetchFavProductsDto.prototype, "price", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.product.thumbnailUrl;
    }),
    __metadata("design:type", Number)
], FetchFavProductsDto.prototype, "thumbnailUrl", void 0);
//# sourceMappingURL=fetch_fav_product.dto.js.map