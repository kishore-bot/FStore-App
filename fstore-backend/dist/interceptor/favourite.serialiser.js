"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.FavoriteSerializerInterceptor = void 0;
exports.FavoriteSerializer = FavoriteSerializer;
const common_1 = require("@nestjs/common");
const class_transformer_1 = require("class-transformer");
const operators_1 = require("rxjs/operators");
function FavoriteSerializer(dto) {
    return (0, common_1.UseInterceptors)(new FavoriteSerializerInterceptor(dto));
}
class FavoriteSerializerInterceptor {
    constructor(dto) {
        this.dto = dto;
    }
    intercept(context, handler) {
        return handler.handle().pipe((0, operators_1.map)((data) => {
            const { favorites, total } = data;
            const transformedProducts = favorites.map((product) => {
                return (0, class_transformer_1.plainToClass)(this.dto, product, {
                    excludeExtraneousValues: true,
                });
            });
            return { total, favorites: transformedProducts };
        }));
    }
}
exports.FavoriteSerializerInterceptor = FavoriteSerializerInterceptor;
//# sourceMappingURL=favourite.serialiser.js.map