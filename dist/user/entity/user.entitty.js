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
exports.User = void 0;
const typeorm_1 = require("typeorm");
const bag_entity_1 = require("../../bag/entity/bag.entity");
const order_entity_1 = require("../../order/entity/order.entity");
const favourite_entity_1 = require("../../favorite/entity/favourite.entity");
const adress_entity_1 = require("./adress.entity");
let User = class User {
};
exports.User = User;
__decorate([
    (0, typeorm_1.PrimaryGeneratedColumn)(),
    __metadata("design:type", Number)
], User.prototype, "id", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'name',
        type: 'varchar',
        length: 15,
        nullable: false,
    }),
    __metadata("design:type", String)
], User.prototype, "name", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'email',
        type: 'varchar',
        length: 255,
        nullable: false,
    }),
    __metadata("design:type", String)
], User.prototype, "email", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'password',
        nullable: false,
    }),
    __metadata("design:type", String)
], User.prototype, "password", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'primary_address',
        type: 'int',
        default: 0,
    }),
    __metadata("design:type", Number)
], User.prototype, "primaryAddress", void 0);
__decorate([
    (0, typeorm_1.OneToMany)(() => favourite_entity_1.Favorite, (favorite) => favorite.user),
    __metadata("design:type", Array)
], User.prototype, "favorites", void 0);
__decorate([
    (0, typeorm_1.OneToMany)(() => bag_entity_1.Bag, (bag) => bag.user),
    __metadata("design:type", Array)
], User.prototype, "bags", void 0);
__decorate([
    (0, typeorm_1.OneToMany)(() => adress_entity_1.Address, (address) => address.user),
    __metadata("design:type", Array)
], User.prototype, "addresses", void 0);
__decorate([
    (0, typeorm_1.OneToMany)(() => order_entity_1.Orders, (order) => order.user),
    __metadata("design:type", Array)
], User.prototype, "orders", void 0);
exports.User = User = __decorate([
    (0, typeorm_1.Entity)()
], User);
//# sourceMappingURL=user.entitty.js.map