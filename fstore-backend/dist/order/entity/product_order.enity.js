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
exports.ProductOrder = void 0;
const typeorm_1 = require("typeorm");
const order_entity_1 = require("./order.entity");
const product_entity_1 = require("../../product/entity/product.entity");
let ProductOrder = class ProductOrder {
};
exports.ProductOrder = ProductOrder;
__decorate([
    (0, typeorm_1.PrimaryGeneratedColumn)(),
    __metadata("design:type", Number)
], ProductOrder.prototype, "id", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'quantity',
        type: 'int',
        nullable: false,
    }),
    __metadata("design:type", Number)
], ProductOrder.prototype, "quantity", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'price',
        type: 'float',
        nullable: false,
    }),
    __metadata("design:type", Number)
], ProductOrder.prototype, "price", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'color',
        type: 'varchar',
        nullable: false,
    }),
    __metadata("design:type", String)
], ProductOrder.prototype, "color", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'size',
        type: 'varchar',
        nullable: false,
    }),
    __metadata("design:type", String)
], ProductOrder.prototype, "size", void 0);
__decorate([
    (0, typeorm_1.CreateDateColumn)({
        name: 'created_at',
        type: 'timestamp',
        default: () => 'CURRENT_TIMESTAMP',
    }),
    __metadata("design:type", Date)
], ProductOrder.prototype, "createdAt", void 0);
__decorate([
    (0, typeorm_1.ManyToOne)(() => order_entity_1.Orders, (order) => order.productOrders),
    __metadata("design:type", order_entity_1.Orders)
], ProductOrder.prototype, "order", void 0);
__decorate([
    (0, typeorm_1.ManyToOne)(() => product_entity_1.Product, (product) => product.productOrders),
    __metadata("design:type", product_entity_1.Product)
], ProductOrder.prototype, "product", void 0);
exports.ProductOrder = ProductOrder = __decorate([
    (0, typeorm_1.Entity)()
], ProductOrder);
//# sourceMappingURL=product_order.enity.js.map