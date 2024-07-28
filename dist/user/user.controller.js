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
exports.UserController = void 0;
const common_1 = require("@nestjs/common");
const user_service_1 = require("./user.service");
const create_adress_dto_1 = require("./dto/create_adress.dto");
const jtw_user_guard_1 = require("../auth/guard/jtw.user.guard");
const get_user_decorator_1 = require("../auth/decorator/get.user.decorator");
const user_entitty_1 = require("./entity/user.entitty");
let UserController = class UserController {
    constructor(userService) {
        this.userService = userService;
    }
    async changeAddress(id, user) {
        return await this.userService.changePrimaryAddress(id, user);
    }
    async fetchPrimaryAddress(user) {
        return await this.userService.fetchPrimaryAddress(user);
    }
    async createAddress(createAddressDto, user) {
        return this.userService.createAddress(createAddressDto, user);
    }
    async getAddress(user) {
        return await this.userService.fetchAddress(user);
    }
    async deleteAddress(id, user) {
        return await this.userService.deleteAddress(user, id);
    }
};
exports.UserController = UserController;
__decorate([
    (0, common_1.Post)('/address/change/:id'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, common_1.Param)('id')),
    __param(1, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Number, user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], UserController.prototype, "changeAddress", null);
__decorate([
    (0, common_1.Get)('/address/primary'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], UserController.prototype, "fetchPrimaryAddress", null);
__decorate([
    (0, common_1.Post)('/address'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, common_1.Body)()),
    __param(1, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [create_adress_dto_1.CreateAddressDto,
        user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], UserController.prototype, "createAddress", null);
__decorate([
    (0, common_1.Get)('/address'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], UserController.prototype, "getAddress", null);
__decorate([
    (0, common_1.Delete)('/address/:id'),
    (0, common_1.UseGuards)(jtw_user_guard_1.JwtUserGuard),
    __param(0, (0, common_1.Param)('id')),
    __param(1, (0, get_user_decorator_1.GetUser)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Number, user_entitty_1.User]),
    __metadata("design:returntype", Promise)
], UserController.prototype, "deleteAddress", null);
exports.UserController = UserController = __decorate([
    (0, common_1.Controller)('user'),
    __metadata("design:paramtypes", [user_service_1.UserService])
], UserController);
//# sourceMappingURL=user.controller.js.map