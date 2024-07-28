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
exports.Orders = void 0;
const adress_entity_1 = require("../../user/entity/adress.entity");
const typeorm_1 = require("typeorm");
const product_order_enity_1 = require("./product_order.enity");
const user_entitty_1 = require("../../user/entity/user.entitty");
let Orders = class Orders {
};
exports.Orders = Orders;
__decorate([
    (0, typeorm_1.PrimaryGeneratedColumn)(),
    __metadata("design:type", Number)
], Orders.prototype, "id", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'orderNo',
        type: 'varchar',
        length: 20,
        unique: true,
    }),
    __metadata("design:type", String)
], Orders.prototype, "orderNo", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'orderId',
        type: 'varchar',
        length: 20,
        unique: true,
    }),
    __metadata("design:type", String)
], Orders.prototype, "orderId", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'totalPrice',
        type: 'float',
        nullable: false,
    }),
    __metadata("design:type", Number)
], Orders.prototype, "totalPrice", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'status',
        type: 'enum',
        enum: ['delivered', 'pending', 'cancelled'],
        default: 'pending',
    }),
    __metadata("design:type", String)
], Orders.prototype, "status", void 0);
__decorate([
    (0, typeorm_1.CreateDateColumn)({
        name: 'created_at',
        type: 'timestamp',
        default: () => 'CURRENT_TIMESTAMP',
    }),
    __metadata("design:type", Date)
], Orders.prototype, "createdAt", void 0);
__decorate([
    (0, typeorm_1.ManyToOne)(() => adress_entity_1.Address, (address) => address.orders),
    __metadata("design:type", adress_entity_1.Address)
], Orders.prototype, "address", void 0);
__decorate([
    (0, typeorm_1.OneToMany)(() => product_order_enity_1.ProductOrder, (productOrder) => productOrder.order),
    __metadata("design:type", Array)
], Orders.prototype, "productOrders", void 0);
__decorate([
    (0, typeorm_1.ManyToOne)(() => user_entitty_1.User, (user) => user.orders, {}),
    __metadata("design:type", user_entitty_1.User)
], Orders.prototype, "user", void 0);
exports.Orders = Orders = __decorate([
    (0, typeorm_1.Entity)()
], Orders);
//# sourceMappingURL=order.entity.js.map