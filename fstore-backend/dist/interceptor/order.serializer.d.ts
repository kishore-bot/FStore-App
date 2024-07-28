import { ExecutionContext, NestInterceptor, CallHandler } from '@nestjs/common';
import { Observable } from 'rxjs';
interface ClassConstructor {
    new (...args: any[]): {};
}
export declare function OrderSerializer(dto: ClassConstructor): MethodDecorator & ClassDecorator;
export declare class OrderSerializerInterceptor implements NestInterceptor {
    private dto;
    constructor(dto: ClassConstructor);
    intercept(context: ExecutionContext, handler: CallHandler): Observable<any>;
}
export {};
