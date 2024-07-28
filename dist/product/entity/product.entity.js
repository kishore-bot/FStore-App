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
exports.Product = void 0;
const typeorm_1 = require("typeorm");
const color_entity_1 = require("./color.entity");
const size_entity_1 = require("./size.entity");
const bag_entity_1 = require("../../bag/entity/bag.entity");
const favourite_entity_1 = require("../../favorite/entity/favourite.entity");
const seller_entitty_1 = require("../../seller/entity/seller.entitty");
const product_order_enity_1 = require("../../order/entity/product_order.enity");
let Product = class Product {
};
exports.Product = Product;
__decorate([
    (0, typeorm_1.PrimaryGeneratedColumn)(),
    __metadata("design:type", Number)
], Product.prototype, "id", void 0);
__decorate([
    (0, typeorm_1.Column)({ name: 'category', type: 'varchar', length: 15, nullable: false }),
    __metadata("design:type", String)
], Product.prototype, "category", void 0);
__decorate([
    (0, typeorm_1.Column)({ name: 'price', type: 'float', nullable: false }),
    __metadata("design:type", Number)
], Product.prototype, "price", void 0);
__decorate([
    (0, typeorm_1.Column)({ name: 'description', type: 'varchar', nullable: false }),
    __metadata("design:type", String)
], Product.prototype, "description", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'mainCategory',
        type: 'enum',
        enum: ['clothes', 'shoes', 'accessories', 'cosmetics'],
        nullable: false,
    }),
    __metadata("design:type", String)
], Product.prototype, "mainCategory", void 0);
__decorate([
    (0, typeorm_1.Column)({ name: 'popularity', type: 'int', default: 0 }),
    __metadata("design:type", Number)
], Product.prototype, "popularity", void 0);
__decorate([
    (0, typeorm_1.Column)({ name: 'discount', type: 'float', default: 0 }),
    __metadata("design:type", Number)
], Product.prototype, "discount", void 0);
__decorate([
    (0, typeorm_1.Column)({ name: 'availability', type: 'int', nullable: false, default: 0 }),
    __metadata("design:type", Number)
], Product.prototype, "availability", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'gender',
        type: 'enum',
        enum: ['male', 'female', 'unisex', 'kids'],
        nullable: false,
    }),
    __metadata("design:type", String)
], Product.prototype, "gender", void 0);
__decorate([
    (0, typeorm_1.Column)({ name: 'rating', type: 'float', default: 0 }),
    __metadata("design:type", Number)
], Product.prototype, "rating", void 0);
__decorate([
    (0, typeorm_1.CreateDateColumn)({
        name: 'created_at',
        type: 'timestamp',
        default: () => 'CURRENT_TIMESTAMP',
    }),
    __metadata("design:type", Date)
], Product.prototype, "createdAt", void 0);
__decorate([
    (0, typeorm_1.UpdateDateColumn)({
        name: 'updated_at',
        type: 'timestamp',
        default: () => 'CURRENT_TIMESTAMP',
        onUpdate: 'CURRENT_TIMESTAMP',
    }),
    __metadata("design:type", Date)
], Product.prototype, "updatedAt", void 0);
__decorate([
    (0, typeorm_1.ManyToMany)(() => color_entity_1.Color, (color) => color.products),
    (0, typeorm_1.JoinTable)(),
    __metadata("design:type", Array)
], Product.prototype, "colors", void 0);
__decorate([
    (0, typeorm_1.ManyToMany)(() => size_entity_1.Size, (size) => size.products),
    (0, typeorm_1.JoinTable)(),
    __metadata("design:type", Array)
], Product.prototype, "sizes", void 0);
__decorate([
    (0, typeorm_1.ManyToOne)(() => seller_entitty_1.Seller, (seller) => seller.products),
    __metadata("design:type", seller_entitty_1.Seller)
], Product.prototype, "seller", void 0);
__decorate([
    (0, typeorm_1.OneToMany)(() => favourite_entity_1.Favorite, (favorite) => favorite.product),
    __metadata("design:type", Array)
], Product.prototype, "favorites", void 0);
__decorate([
    (0, typeorm_1.OneToMany)(() => bag_entity_1.Bag, (bag) => bag.product),
    __metadata("design:type", Array)
], Product.prototype, "bagItems", void 0);
__decorate([
    (0, typeorm_1.OneToMany)(() => product_order_enity_1.ProductOrder, (productOrder) => productOrder.product),
    __metadata("design:type", Array)
], Product.prototype, "productOrders", void 0);
exports.Product = Product = __decorate([
    (0, typeorm_1.Entity)()
], Product);
//# sourceMappingURL=product.entity.js.map