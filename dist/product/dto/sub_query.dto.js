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
exports.SubQueryDto = void 0;
const class_transformer_1 = require("class-transformer");
const class_validator_1 = require("class-validator");
var Gender;
(function (Gender) {
    Gender["MALE"] = "male";
    Gender["FEMALE"] = "female";
    Gender["UNISEX"] = "unisex";
    Gender["KIDS"] = "kids";
})(Gender || (Gender = {}));
class SubQueryDto {
}
exports.SubQueryDto = SubQueryDto;
__decorate([
    (0, class_transformer_1.Transform)(({ value }) => {
        if (typeof value === 'string' &&
            Object.values(Gender).includes(value.toLowerCase())) {
            return value.toLowerCase();
        }
        return undefined;
    }),
    (0, class_validator_1.IsOptional)(),
    (0, class_validator_1.IsEnum)(Gender),
    __metadata("design:type", String)
], SubQueryDto.prototype, "gender", void 0);
__decorate([
    (0, class_transformer_1.Transform)(({ value }) => value.toLowerCase()),
    (0, class_validator_1.IsOptional)(),
    (0, class_validator_1.IsString)(),
    __metadata("design:type", String)
], SubQueryDto.prototype, "mainCategory", void 0);
//# sourceMappingURL=sub_query.dto.js.map