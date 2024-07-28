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
exports.OrderService = void 0;
const common_1 = require("@nestjs/common");
const typeorm_1 = require("@nestjs/typeorm");
const order_entity_1 = require("./entity/order.entity");
const typeorm_2 = require("typeorm");
const product_order_enity_1 = require("./entity/product_order.enity");
const bag_service_1 = require("../bag/bag.service");
const user_service_1 = require("../user/user.service");
const product_service_1 = require("../product/product.service");
let OrderService = class OrderService {
    constructor(orderRepo, productOrderRepo, bagsService, userService, productService) {
        this.orderRepo = orderRepo;
        this.productOrderRepo = productOrderRepo;
        this.bagsService = bagsService;
        this.userService = userService;
        this.productService = productService;
    }
    generateOrderId() {
        const randomNumber = Math.floor(1000000 + Math.random() * 9000000);
        return randomNumber.toString();
    }
    generateRandomString(length = 12) {
        const charset = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        let result = '';
        const charsetLength = charset.length;
        for (let i = 0; i < length; i++) {
            const randomIndex = Math.floor(Math.random() * charsetLength);
            result += charset[randomIndex];
        }
        return result;
    }
    async placeOrders(user) {
        const address = await this.userService.fetchPrimaryAddress(user);
        const bags = await this.bagsService.fetchBagsForOrders(user);
        if (bags.length === 0) {
            throw new common_1.BadRequestException('No items found in bags');
        }
        const order = this.orderRepo.create({
            orderNo: this.generateOrderId(),
            orderId: this.generateRandomString(),
            totalPrice: 0,
            address: address,
            user: user,
        });
        await this.orderRepo.save(order);
        const productOrdersPromises = bags.map(async (bag) => {
            const { product, quantityNo } = await this.productService.productForOrder(bag.product.id, bag.quantity);
            return this.productOrderRepo.create({
                quantity: quantityNo,
                price: product.price,
                color: bag.color,
                size: bag.size,
                order: order,
                product: product,
            });
        });
        const productOrders = await Promise.all(productOrdersPromises);
        await this.productOrderRepo.save(productOrders);
        const totalPrice = productOrders.reduce((sum, po) => sum + po.price * po.quantity, 0);
        order.totalPrice = totalPrice;
        await this.orderRepo.save(order);
        await this.bagsService.clearBagsForOrders(user);
        return { message: 'Order Placed SuccessFully' };
    }
    async fetchOrders(user) {
        const orders = await this.orderRepo
            .createQueryBuilder('order')
            .leftJoinAndSelect('order.user', 'user')
            .leftJoinAndSelect('order.productOrders', 'productOrder')
            .leftJoinAndSelect('productOrder.product', 'product')
            .where('user.id = :userId', { userId: user.id })
            .getMany();
        console.log(orders);
        return orders;
    }
    async fetchOrderDetails() { }
};
exports.OrderService = OrderService;
exports.OrderService = OrderService = __decorate([
    (0, common_1.Injectable)(),
    __param(0, (0, typeorm_1.InjectRepository)(order_entity_1.Orders)),
    __param(1, (0, typeorm_1.InjectRepository)(product_order_enity_1.ProductOrder)),
    __metadata("design:paramtypes", [typeorm_2.Repository,
        typeorm_2.Repository,
        bag_service_1.BagService,
        user_service_1.UserService,
        product_service_1.ProductService])
], OrderService);
//# sourceMappingURL=order.service.js.map