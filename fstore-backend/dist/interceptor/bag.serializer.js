"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.BagSerializerInterceptor = void 0;
exports.BagSerializer = BagSerializer;
const common_1 = require("@nestjs/common");
const class_transformer_1 = require("class-transformer");
const operators_1 = require("rxjs/operators");
function BagSerializer(dto) {
    return (0, common_1.UseInterceptors)(new BagSerializerInterceptor(dto));
}
class BagSerializerInterceptor {
    constructor(dto) {
        this.dto = dto;
    }
    intercept(context, handler) {
        return handler.handle().pipe((0, operators_1.map)((data) => {
            const { bags, total } = data;
            const transformedBags = bags.map((bag) => {
                return (0, class_transformer_1.plainToClass)(this.dto, bag, {
                    excludeExtraneousValues: true,
                });
            });
            return { total, bags: transformedBags };
        }));
    }
}
exports.BagSerializerInterceptor = BagSerializerInterceptor;
//# sourceMappingURL=bag.serializer.js.map