import { ExecutionContext, NestInterceptor, CallHandler } from '@nestjs/common';
import { Observable } from 'rxjs';
interface ClassConstructor {
    new (...args: any[]): {};
}
export declare function FetchproductSerializer(dto: ClassConstructor): MethodDecorator & ClassDecorator;
export declare class FetchproductSerializerInterceptor implements NestInterceptor {
    private dto;
    constructor(dto: any);
    intercept(context: ExecutionContext, handler: CallHandler): Observable<any>;
}
export {};
