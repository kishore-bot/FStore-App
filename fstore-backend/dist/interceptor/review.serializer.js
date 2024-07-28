"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ReviewSerializerInterceptor = void 0;
exports.ReviewSerializer = ReviewSerializer;
const common_1 = require("@nestjs/common");
const class_transformer_1 = require("class-transformer");
const operators_1 = require("rxjs/operators");
function ReviewSerializer(dto) {
    return (0, common_1.UseInterceptors)(new ReviewSerializerInterceptor(dto));
}
class ReviewSerializerInterceptor {
    constructor(dto) {
        this.dto = dto;
    }
    intercept(context, handler) {
        return handler.handle().pipe((0, operators_1.map)((data) => {
            const { review, total } = data;
            const transformedreviews = review.map((bag) => {
                return (0, class_transformer_1.plainToClass)(this.dto, bag, {
                    excludeExtraneousValues: true,
                });
            });
            return { total, review: transformedreviews };
        }));
    }
}
exports.ReviewSerializerInterceptor = ReviewSerializerInterceptor;
//# sourceMappingURL=review.serializer.js.map