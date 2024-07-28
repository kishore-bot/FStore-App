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
exports.AuthService = void 0;
const common_1 = require("@nestjs/common");
const typeorm_1 = require("@nestjs/typeorm");
const user_entitty_1 = require("../user/entity/user.entitty");
const typeorm_2 = require("typeorm");
const crypto_1 = require("crypto");
const util_1 = require("util");
const jwt_1 = require("@nestjs/jwt");
const seller_entitty_1 = require("../seller/entity/seller.entitty");
const scrypt = (0, util_1.promisify)(crypto_1.scrypt);
let AuthService = class AuthService {
    constructor(userRepo, sellerRepo, jwtService) {
        this.userRepo = userRepo;
        this.sellerRepo = sellerRepo;
        this.jwtService = jwtService;
    }
    async registerUser(attr) {
        try {
            const email = attr.email;
            const userEmail = await this.userRepo.findOne({ where: { email } });
            if (userEmail != null) {
                throw new common_1.HttpException('Email already exists', common_1.HttpStatus.UNAUTHORIZED);
            }
            const hashPass = await this.passwordHasher(attr.password);
            attr.password = hashPass;
            const user = this.userRepo.create(attr);
            await this.userRepo.save(user);
            const { password, name, ...userWithNoPass } = user;
            const token = await this.jwtService.signAsync(userWithNoPass);
            return { token };
        }
        catch (e) {
            throw new common_1.HttpException(e.message, common_1.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    async registerSeller(attr) {
        try {
            const email = attr.email;
            const admin = await this.sellerRepo.findOne({ where: { email } });
            if (admin != null) {
                throw new common_1.HttpException('Email already exists', common_1.HttpStatus.UNAUTHORIZED);
            }
            const hashPass = await this.passwordHasher(attr.password);
            attr.password = hashPass;
            const admins = this.sellerRepo.create(attr);
            await this.sellerRepo.save(admins);
            const { password, name, ...userWithNoPass } = admins;
            const token = await this.jwtService.signAsync(userWithNoPass);
            return { token };
        }
        catch (e) {
            throw new common_1.HttpException(e.message, common_1.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    async validateUser(attr) {
        try {
            const email = attr.email;
            const userEmail = await this.userRepo.findOne({ where: { email } });
            if (userEmail == null) {
                throw new common_1.HttpException('No account exist on this email', common_1.HttpStatus.UNAUTHORIZED);
            }
            const [salt, storedHash] = userEmail.password.split('.');
            const hash = (await scrypt(attr.password, salt, 32));
            if (storedHash !== hash.toString('hex')) {
                throw new common_1.BadRequestException('Invalid Password');
            }
            const { password, name, ...userWithNoPass } = userEmail;
            const token = await this.jwtService.signAsync(userWithNoPass);
            return { token };
        }
        catch (e) {
            throw new common_1.HttpException(e.message, common_1.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    async validateSeller(attr) {
        try {
            const email = attr.email;
            const userEmail = await this.sellerRepo.findOne({ where: { email } });
            if (userEmail == null) {
                throw new common_1.HttpException('No account exist on this email', common_1.HttpStatus.UNAUTHORIZED);
            }
            const [salt, storedHash] = userEmail.password.split('.');
            const hash = (await scrypt(attr.password, salt, 32));
            if (storedHash !== hash.toString('hex')) {
                throw new common_1.BadRequestException('Invalid Password');
            }
            const { password, name, ...userWithNoPass } = userEmail;
            const token = await this.jwtService.signAsync(userWithNoPass);
            return { token };
        }
        catch (e) {
            throw new common_1.HttpException(e.message, common_1.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    async passwordHasher(pass) {
        const salt = (0, crypto_1.randomBytes)(8).toString('hex');
        const hash = (await scrypt(pass, salt, 32));
        const result = salt + '.' + hash.toString('hex');
        return result;
    }
    async verifyUser(id) {
        const userId = parseInt(id, 10);
        if (isNaN(userId)) {
            return null;
        }
        const user = await this.userRepo.findOne({ where: { id: userId } });
        return user || null;
    }
    async verifySeller(id) {
        const userId = parseInt(id, 10);
        if (isNaN(userId)) {
            return null;
        }
        const user = await this.sellerRepo.findOne({ where: { id: userId } });
        return user || null;
    }
};
exports.AuthService = AuthService;
exports.AuthService = AuthService = __decorate([
    (0, common_1.Injectable)(),
    __param(0, (0, typeorm_1.InjectRepository)(user_entitty_1.User)),
    __param(1, (0, typeorm_1.InjectRepository)(seller_entitty_1.Seller)),
    __metadata("design:paramtypes", [typeorm_2.Repository,
        typeorm_2.Repository,
        jwt_1.JwtService])
], AuthService);
//# sourceMappingURL=auth.service.js.map