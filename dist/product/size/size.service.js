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
exports.SizeService = void 0;
const common_1 = require("@nestjs/common");
const typeorm_1 = require("@nestjs/typeorm");
const size_entity_1 = require("../entity/size.entity");
const typeorm_2 = require("typeorm");
let SizeService = class SizeService {
    constructor(sizeRepo) {
        this.sizeRepo = sizeRepo;
    }
    async createSize(sizeDto) {
        const sizes = await Promise.all(sizeDto.map(async (sizeName) => {
            let size = await this.sizeRepo.findOne({ where: { name: sizeName } });
            if (!size) {
                size = this.sizeRepo.create({ name: sizeName });
                await this.sizeRepo.save(size);
            }
            return size;
        }));
        return sizes;
    }
};
exports.SizeService = SizeService;
exports.SizeService = SizeService = __decorate([
    (0, common_1.Injectable)(),
    __param(0, (0, typeorm_1.InjectRepository)(size_entity_1.Size)),
    __metadata("design:paramtypes", [typeorm_2.Repository])
], SizeService);
//# sourceMappingURL=size.service.js.map