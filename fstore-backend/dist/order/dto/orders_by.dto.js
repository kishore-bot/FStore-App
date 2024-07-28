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
exports.OrderByDto = exports.OrderProduct = exports.OrderAddress = void 0;
const class_transformer_1 = require("class-transformer");
class OrderAddress {
}
exports.OrderAddress = OrderAddress;
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return ((obj.address || '') +
            ' ,' +
            (obj.city || '') +
            '\n' +
            (obj.state || '') +
            ' ,' +
            (obj.pincode || '') +
            ' ,' +
            (obj.country || '')).trim();
    }),
    __metadata("design:type", String)
], OrderAddress.prototype, "address", void 0);
class OrderProduct {
}
exports.OrderProduct = OrderProduct;
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => obj.product.seller.name),
    __metadata("design:type", String)
], OrderProduct.prototype, "name", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => obj.size),
    __metadata("design:type", String)
], OrderProduct.prototype, "size", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => obj.color),
    __metadata("design:type", String)
], OrderProduct.prototype, "color", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => obj.product?.category),
    __metadata("design:type", String)
], OrderProduct.prototype, "category", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => Math.round(obj.price)),
    __metadata("design:type", Number)
], OrderProduct.prototype, "price", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => obj.quantity),
    __metadata("design:type", Number)
], OrderProduct.prototype, "quantity", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => obj.product.thumbnailUrl),
    __metadata("design:type", String)
], OrderProduct.prototype, "thumbnailUrl", void 0);
class OrderByDto {
}
exports.OrderByDto = OrderByDto;
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Number)
], OrderByDto.prototype, "id", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", String)
], OrderByDto.prototype, "orderNo", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", String)
], OrderByDto.prototype, "orderId", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Number)
], OrderByDto.prototype, "totalPrice", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Date)
], OrderByDto.prototype, "createdAt", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => obj.productOrdersCount || 0),
    __metadata("design:type", Number)
], OrderByDto.prototype, "quantity", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Type)(() => OrderAddress),
    __metadata("design:type", OrderAddress)
], OrderByDto.prototype, "address", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Type)(() => OrderProduct),
    (0, class_transformer_1.Transform)(({ obj }) => {
        return obj.productOrders.map(item => (0, class_transformer_1.plainToClass)(OrderProduct, item, { excludeExtraneousValues: true }));
    }),
    __metadata("design:type", OrderProduct)
], OrderByDto.prototype, "orderProducts", void 0);
//# sourceMappingURL=orders_by.dto.js.map