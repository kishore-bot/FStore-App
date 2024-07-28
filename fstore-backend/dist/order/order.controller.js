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
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.OrderController = void 0;
const common_1 = require("@nestjs/common");
const order_service_1 = require("./order.service");
const jtw_user_guard_1 = require("../auth/guard/jtw.user.guard");
const get_user_decorator_1 = require("../auth/decorator/get.user.decorator");
const user_entitty_1 = require("../user/entity/user.entitty");
const order_serializer_1 = require("../interceptor/order.serializer");
const orders_dto_1 = require("./dto/orders.dto");
const orders_by_dto_1 = require("./dto/orders_by.dto");
const serialize_interceptor_1 = require("../interceptor/serialize.interceptor");
let OrderController = class OrderController {
    constructor(orderService) {
        this.orderService = orderService;
    }
    async isOrdered(user, id) {
        return await this.orderService.isProductOrdered(id, user);
    }
    async fetchById(user, id) {
        const order = await this.orderService.fetchOrderDetails(user, id);
        return order;
    }
    async placeOrder(user) {
        const order = await this.orderService.placeOrders(user);
        return order;
    }
    async fetchOrder(user, page) {
        const order = await this.orderService.fetchOrders(user, page);
        return order;
    }
};
exports.OrderController = OrderController;
__decorate([
    (0, common_1.Get)('/isOrdered/:id'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, get_user_decorator_1.GetUser)()),
    __param(1, (0, common_1.Param)('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [user_entitty_1.User, Number]),
    __metadata("design:returntype", Promise)
], OrderController.prototype, "isOrdered", null);
__decorate([
    (0, common_1.Get)('/by/:id'),
    (0, serialize_interceptor_1.Serialize)(orders_by_dto_1.OrderByDto),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, get_user_decorator_1.GetUser)()),
    __param(1, (0, common_1.Param)('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [user_entitty_1.User, Number]),
    __metadata("design:returntype", Promise)
], OrderController.prototype, "fetchById", null);
__decorate([
    (0, common_1.Post)(),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], OrderController.prototype, "placeOrder", null);
__decorate([
    (0, common_1.Get)('/:page'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    (0, order_serializer_1.OrderSerializer)(orders_dto_1.OrdersDto),
    __param(0, (0, get_user_decorator_1.GetUser)()),
    __param(1, (0, common_1.Param)('page')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [user_entitty_1.User, Number]),
    __metadata("design:returntype", Promise)
], OrderController.prototype, "fetchOrder", null);
exports.OrderController = OrderController = __decorate([
    (0, common_1.Controller)('order'),
    __metadata("design:paramtypes", [order_service_1.OrderService])
], OrderController);
//# sourceMappingURL=order.controller.js.map