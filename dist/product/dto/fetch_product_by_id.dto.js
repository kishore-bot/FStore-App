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
exports.FetchProductById = void 0;
const class_transformer_1 = require("class-transformer");
class FetchProductById {
}
exports.FetchProductById = FetchProductById;
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Number)
], FetchProductById.prototype, "id", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Number)
], FetchProductById.prototype, "price", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", String)
], FetchProductById.prototype, "category", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", String)
], FetchProductById.prototype, "mainCategory", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", String)
], FetchProductById.prototype, "gender", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Array)
], FetchProductById.prototype, "size", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Array)
], FetchProductById.prototype, "color", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", String)
], FetchProductById.prototype, "description", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Number)
], FetchProductById.prototype, "popularity", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, class_transformer_1.Transform)(({ obj }) => obj.availability > 0),
    __metadata("design:type", Boolean)
], FetchProductById.prototype, "available", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Number)
], FetchProductById.prototype, "rating", void 0);
__decorate([
    (0, class_transformer_1.Transform)(({ obj }) => obj.seller.name),
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", String)
], FetchProductById.prototype, "name", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    __metadata("design:type", Boolean)
], FetchProductById.prototype, "favorite", void 0);
//# sourceMappingURL=fetch_product_by_id.dto.js.map