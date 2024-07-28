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
exports.FetchProductsDto = void 0;
const common_1 = require("@nestjs/common");
const class_transformer_1 = require("class-transformer");
class FetchProductsDto {
}
exports.FetchProductsDto = FetchProductsDto;
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Number)
], FetchProductsDto.prototype, "id", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Number)
], FetchProductsDto.prototype, "price", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", String)
], FetchProductsDto.prototype, "category", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, common_1.Optional)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        if (obj.discount != 0) {
            const discountedPrice = obj.price - (obj.price * obj.discount) / 100;
            return Math.round(discountedPrice);
        }
        return 0;
    }),
    __metadata("design:type", Number)
], FetchProductsDto.prototype, "dPrice", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Number)
], FetchProductsDto.prototype, "rating", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Number)
], FetchProductsDto.prototype, "discount", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        const oneMonthInMilliseconds = 30 * 24 * 60 * 60 * 1000;
        const isWithinOneMonth = (Date.now() - new Date(obj.createdAt).getTime()) <= oneMonthInMilliseconds;
        return isWithinOneMonth;
    }),
    __metadata("design:type", Boolean)
], FetchProductsDto.prototype, "new", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => obj.seller.name),
    __metadata("design:type", String)
], FetchProductsDto.prototype, "name", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => obj.isFavorite),
    __metadata("design:type", Boolean)
], FetchProductsDto.prototype, "isFav", void 0);
//# sourceMappingURL=Fetch_product.dto.js.map