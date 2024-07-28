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
exports.BagController = void 0;
const common_1 = require("@nestjs/common");
const bag_service_1 = require("./bag.service");
const jtw_user_guard_1 = require("../auth/guard/jtw.user.guard");
const get_user_decorator_1 = require("../auth/decorator/get.user.decorator");
const user_entitty_1 = require("../user/entity/user.entitty");
const bag_serializer_1 = require("../interceptor/bag.serializer");
const Fetch_bag_dto_1 = require("./dto/Fetch_bag.dto");
const update_qunatity_dto_1 = require("./dto/update_qunatity.dto");
const create_bag_dto_1 = require("./dto/create_bag.dto");
let BagController = class BagController {
    constructor(bagService) {
        this.bagService = bagService;
    }
    async createBag(bag, user) {
        return await this.bagService.createBag(bag, user);
    }
    async fetchTotalPrice(user) {
        return await this.bagService.getTotalPrice(user);
    }
    async fetchBags(page, user) {
        return await this.bagService.fetchBags(page, user);
    }
    async deleteBags(id) {
        return await this.bagService.deleteBags(id);
    }
    async updateQunatity(bag) {
        return await this.bagService.updateQuantity(bag);
    }
};
exports.BagController = BagController;
__decorate([
    (0, common_1.Post)(),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, common_1.Body)()),
    __param(1, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [create_bag_dto_1.CreateBagDto, user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], BagController.prototype, "createBag", null);
__decorate([
    (0, common_1.Get)('/price'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], BagController.prototype, "fetchTotalPrice", null);
__decorate([
    (0, common_1.Get)('/:page'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    (0, bag_serializer_1.BagSerializer)(Fetch_bag_dto_1.FetchBagdto),
    __param(0, (0, common_1.Param)('page')),
    __param(1, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Number, user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], BagController.prototype, "fetchBags", null);
__decorate([
    (0, common_1.Delete)('/:id'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, common_1.Param)('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Number]),
    __metadata("design:returntype", Promise)
], BagController.prototype, "deleteBags", null);
__decorate([
    (0, common_1.Patch)(),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, common_1.Body)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [update_qunatity_dto_1.UpdateQuantityDto]),
    __metadata("design:returntype", Promise)
], BagController.prototype, "updateQunatity", null);
exports.BagController = BagController = __decorate([
    (0, common_1.Controller)('bag'),
    __metadata("design:paramtypes", [bag_service_1.BagService])
], BagController);
//# sourceMappingURL=bag.controller.js.map