"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.OrderSerializerInterceptor = void 0;
exports.OrderSerializer = OrderSerializer;
const common_1 = require("@nestjs/common");
const class_transformer_1 = require("class-transformer");
const operators_1 = require("rxjs/operators");
function OrderSerializer(dto) {
    return (0, common_1.UseInterceptors)(new OrderSerializerInterceptor(dto));
}
class OrderSerializerInterceptor {
    constructor(dto) {
        this.dto = dto;
    }
    intercept(context, handler) {
        return handler.handle().pipe((0, operators_1.map)((data) => {
            const { orders, total } = data;
            const transformedOrders = orders.map((bag) => {
                return (0, class_transformer_1.plainToClass)(this.dto, bag, {
                    excludeExtraneousValues: true,
                });
            });
            return { total, orders: transformedOrders };
        }));
    }
}
exports.OrderSerializerInterceptor = OrderSerializerInterceptor;
//# sourceMappingURL=order.serializer.js.map