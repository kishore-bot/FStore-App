"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.BagModule = void 0;
const common_1 = require("@nestjs/common");
const bag_service_1 = require("./bag.service");
const bag_controller_1 = require("./bag.controller");
const typeorm_1 = require("@nestjs/typeorm");
const bag_entity_1 = require("./entity/bag.entity");
const product_module_1 = require("../product/product.module");
const aws_module_1 = require("../aws/aws.module");
let BagModule = class BagModule {
};
exports.BagModule = BagModule;
exports.BagModule = BagModule = __decorate([
    (0, common_1.Module)({
        imports: [typeorm_1.TypeOrmModule.forFeature([bag_entity_1.Bag]), product_module_1.ProductModule, aws_module_1.AwsModule],
        providers: [bag_service_1.BagService],
        controllers: [bag_controller_1.BagController],
        exports: [bag_service_1.BagService]
    })
], BagModule);
//# sourceMappingURL=bag.module.js.map