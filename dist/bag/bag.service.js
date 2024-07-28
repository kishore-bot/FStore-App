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
exports.BagService = void 0;
const common_1 = require("@nestjs/common");
const bag_entity_1 = require("./entity/bag.entity");
const typeorm_1 = require("@nestjs/typeorm");
const typeorm_2 = require("typeorm");
const product_service_1 = require("../product/product.service");
let BagService = class BagService {
    constructor(bagRepo, productService) {
        this.bagRepo = bagRepo;
        this.productService = productService;
    }
    async createBag(bagDto, user) {
        const product = await this.productService.fetchProduct(bagDto.productId);
        if (!product || product.availability <= 0) {
            throw new Error('Product not found');
        }
        var price;
        if (bagDto.price == 0) {
            if (product.discount == 0) {
                price = product.price;
            }
            else {
                price = product.price - product.price * (product.discount / 100);
            }
        }
        else {
            price = bagDto.price;
        }
        const bag = this.bagRepo.create({
            product: product,
            user: user,
            quantity: 1,
            price: price,
            color: bagDto.color,
            size: bagDto.size,
        });
        await this.bagRepo.save(bag);
        return { message: 'Added to bag' };
    }
    async fetchBags(page, user) {
        const [bags, totalItems] = await this.bagRepo
            .createQueryBuilder('bag')
            .leftJoinAndSelect('bag.product', 'product')
            .where('bag.userId = :userId', { userId: user.id })
            .andWhere('product.availability > 0')
            .orderBy('bag.createdAt', 'DESC')
            .skip((page - 1) * 5)
            .take(5)
            .getManyAndCount();
        return {
            bags: bags,
            total: totalItems,
        };
    }
    async getTotalPrice(user) {
        const totalPriceResult = await this.bagRepo
            .createQueryBuilder('bag')
            .leftJoin('bag.product', 'product')
            .select('SUM(bag.price * bag.quantity)', 'sum')
            .where('bag.userId = :userId', { userId: user.id })
            .andWhere('product.availability > 0')
            .getRawOne();
        const price = parseFloat(totalPriceResult.sum) || 0;
        return { price };
    }
    async deleteBags(uid) {
        const bag = await this.bagRepo.findOne({ where: { id: uid } });
        if (!bag) {
            throw new Error('Bag not found');
        }
        await this.bagRepo.remove(bag);
        return { message: 'SuccessFully removed ' };
    }
    async updateQuantity(quantity) {
        const bag = await this.bagRepo.findOne({ where: { id: quantity.id } });
        bag.quantity = quantity.quantity;
        await this.bagRepo.save(quantity);
        return { message: 'Updated Qunatity' };
    }
    async fetchBagsForOrders(user) {
        const bags = await this.bagRepo
            .createQueryBuilder('bag')
            .leftJoinAndSelect('bag.product', 'product')
            .where('bag.userId = :userId', { userId: user.id })
            .andWhere('product.availability > 0')
            .orderBy('bag.createdAt', 'DESC')
            .getMany();
        return bags;
    }
    async clearBagsForOrders(user) {
        await this.bagRepo.delete({ user: { id: user.id } });
    }
};
exports.BagService = BagService;
exports.BagService = BagService = __decorate([
    (0, common_1.Injectable)(),
    __param(0, (0, typeorm_1.InjectRepository)(bag_entity_1.Bag)),
    __metadata("design:paramtypes", [typeorm_2.Repository,
        product_service_1.ProductService])
], BagService);
//# sourceMappingURL=bag.service.js.map