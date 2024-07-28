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
exports.UserService = void 0;
const common_1 = require("@nestjs/common");
const typeorm_1 = require("@nestjs/typeorm");
const typeorm_2 = require("typeorm");
const user_entitty_1 = require("./entity/user.entitty");
const adress_entity_1 = require("./entity/adress.entity");
let UserService = class UserService {
    constructor(userRepo, addressRepo) {
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
    }
    async createAddress(createAddressDto, user) {
        const address = this.addressRepo.create({
            ...createAddressDto,
            user: user,
        });
        await this.addressRepo.save(address);
        if (user.primaryAddress == 0) {
            user.primaryAddress = address.id;
            await this.userRepo.save(user);
        }
        return { message: 'Created Address' };
    }
    async fetchAddress(user) {
        const addresses = await this.addressRepo.find({
            where: { user: user },
        });
        return {
            addresses: addresses.map((address) => ({
                id: address.id,
                name: address.name,
                address: address.address,
                city: address.city,
                state: address.state,
                pincode: address.pincode,
                country: address.country,
            })),
            primaryAddress: user.primaryAddress,
        };
    }
    async deleteAddress(user, addressId) {
        const address = await this.addressRepo.findOne({
            where: { id: addressId },
        });
        if (address.id === user.primaryAddress) {
            user.primaryAddress = 0;
            await this.userRepo.save(user);
        }
        await this.addressRepo.remove(address);
        return { message: 'Address Deleted' };
    }
    async fetchPrimaryAddress(user) {
        const address = await this.addressRepo.findOneBy({
            id: user.primaryAddress,
        });
        if (address == null) {
            return {
                address: null,
                city: null,
                country: null,
                id: null,
                name: null,
                pincode: null,
                state: null,
            };
        }
        return address;
    }
    async changePrimaryAddress(adddressId, user) {
        const address = await this.addressRepo.findOneBy({ id: adddressId });
        if (!address) {
            return { message: 'No Address Found' };
        }
        user.primaryAddress = address.id;
        await this.userRepo.save(user);
        return { message: 'Changes Address' };
    }
};
exports.UserService = UserService;
exports.UserService = UserService = __decorate([
    (0, common_1.Injectable)(),
    __param(0, (0, typeorm_1.InjectRepository)(user_entitty_1.User)),
    __param(1, (0, typeorm_1.InjectRepository)(adress_entity_1.Address)),
    __metadata("design:paramtypes", [typeorm_2.Repository,
        typeorm_2.Repository])
], UserService);
//# sourceMappingURL=user.service.js.map