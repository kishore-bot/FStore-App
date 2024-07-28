"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.GetSeller = void 0;
const common_1 = require("@nestjs/common");
exports.GetSeller = (0, common_1.createParamDecorator)((data, ctx) => {
    const request = ctx.switchToHttp().getRequest();
    if (data) {
        return request.user[data];
    }
    return request.user;
});
//# sourceMappingURL=get.seller.decorator.js.map