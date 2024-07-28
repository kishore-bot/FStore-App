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
exports.Bag = void 0;
const product_entity_1 = require("../../product/entity/product.entity");
const user_entitty_1 = require("../../user/entity/user.entitty");
const typeorm_1 = require("typeorm");
let Bag = class Bag {
};
exports.Bag = Bag;
__decorate([
    (0, typeorm_1.PrimaryGeneratedColumn)(),
    __metadata("design:type", Number)
], Bag.prototype, "id", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'quantity',
        type: 'int',
    }),
    __metadata("design:type", Number)
], Bag.prototype, "quantity", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'price',
        type: 'float',
    }),
    __metadata("design:type", Number)
], Bag.prototype, "price", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'color',
        type: 'varchar',
    }),
    __metadata("design:type", String)
], Bag.prototype, "color", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'size',
        type: 'varchar',
    }),
    __metadata("design:type", String)
], Bag.prototype, "size", void 0);
__decorate([
    (0, typeorm_1.ManyToOne)(() => product_entity_1.Product, (product) => product.bagItems),
    __metadata("design:type", product_entity_1.Product)
], Bag.prototype, "product", void 0);
__decorate([
    (0, typeorm_1.ManyToOne)(() => user_entitty_1.User, (user) => user.bags),
    __metadata("design:type", user_entitty_1.User)
], Bag.prototype, "user", void 0);
__decorate([
    (0, typeorm_1.CreateDateColumn)({
        name: 'created_at',
        type: 'timestamp',
        default: () => 'CURRENT_TIMESTAMP',
    }),
    __metadata("design:type", Date)
], Bag.prototype, "createdAt", void 0);
exports.Bag = Bag = __decorate([
    (0, typeorm_1.Entity)()
], Bag);
//# sourceMappingURL=bag.entity.js.map