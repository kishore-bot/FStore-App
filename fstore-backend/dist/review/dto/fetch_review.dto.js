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
exports.FetchReviewsDto = void 0;
const common_1 = require("@nestjs/common");
const class_transformer_1 = require("class-transformer");
class FetchReviewsDto {
}
exports.FetchReviewsDto = FetchReviewsDto;
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, common_1.Optional)(),
    __metadata("design:type", Number)
], FetchReviewsDto.prototype, "id", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, common_1.Optional)(),
    (0, class_transformer_1.Transform)(({ obj }) => obj.user.name),
    __metadata("design:type", String)
], FetchReviewsDto.prototype, "user", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, common_1.Optional)(),
    __metadata("design:type", String)
], FetchReviewsDto.prototype, "createdAt", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, common_1.Optional)(),
    __metadata("design:type", String)
], FetchReviewsDto.prototype, "comment", void 0);
__decorate([
    (0, class_transformer_1.Expose)(),
    (0, common_1.Optional)(),
    __metadata("design:type", String)
], FetchReviewsDto.prototype, "rating", void 0);
//# sourceMappingURL=fetch_review.dto.js.map