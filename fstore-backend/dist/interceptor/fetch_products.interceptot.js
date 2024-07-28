"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.FetchproductSerializerInterceptor = void 0;
exports.FetchproductSerializer = FetchproductSerializer;
const common_1 = require("@nestjs/common");
const class_transformer_1 = require("class-transformer");
const operators_1 = require("rxjs/operators");
function FetchproductSerializer(dto) {
    return (0, common_1.UseInterceptors)(new FetchproductSerializerInterceptor(dto));
}
class FetchproductSerializerInterceptor {
    constructor(dto) {
        this.dto = dto;
    }
    intercept(context, handler) {
        return handler.handle().pipe((0, operators_1.map)((data) => {
            const { products, total } = data;
            const transformedProducts = products.map((product) => {
                return (0, class_transformer_1.plainToClass)(this.dto, product, {
                    excludeExtraneousValues: true,
                });
            });
            return { total, products: transformedProducts };
        }));
    }
}
exports.FetchproductSerializerInterceptor = FetchproductSerializerInterceptor;
//# sourceMappingURL=fetch_products.interceptot.js.map