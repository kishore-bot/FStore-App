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
exports.Favorite = void 0;
const product_entity_1 = require("../../product/entity/product.entity");
const user_entitty_1 = require("../../user/entity/user.entitty");
const typeorm_1 = require("typeorm");
let Favorite = class Favorite {
};
exports.Favorite = Favorite;
__decorate([
    (0, typeorm_1.PrimaryGeneratedColumn)(),
    __metadata("design:type", Number)
], Favorite.prototype, "id", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'size',
        type: 'varchar',
        length: 255,
        nullable: false,
    }),
    __metadata("design:type", String)
], Favorite.prototype, "size", void 0);
__decorate([
    (0, typeorm_1.Column)({
        name: 'color',
        type: 'varchar',
        length: 255,
        nullable: false,
    }),
    __metadata("design:type", String)
], Favorite.prototype, "color", void 0);
__decorate([
    (0, typeorm_1.ManyToOne)(() => user_entitty_1.User, (user) => user.favorites),
    __metadata("design:type", user_entitty_1.User)
], Favorite.prototype, "user", void 0);
__decorate([
    (0, typeorm_1.ManyToOne)(() => product_entity_1.Product, (product) => product.favorites, { nullable: false }),
    __metadata("design:type", product_entity_1.Product)
], Favorite.prototype, "product", void 0);
__decorate([
    (0, typeorm_1.CreateDateColumn)({
        name: 'created_at',
        type: 'timestamp',
        default: () => 'CURRENT_TIMESTAMP',
    }),
    __metadata("design:type", Date)
], Favorite.prototype, "createdAt", void 0);
exports.Favorite = Favorite = __decorate([
    (0, typeorm_1.Entity)()
], Favorite);
//# sourceMappingURL=favourite.entity.js.map