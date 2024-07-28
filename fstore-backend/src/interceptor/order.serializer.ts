import {
    UseInterceptors,
    ExecutionContext,
    NestInterceptor,
    CallHandler,
  } from '@nestjs/common';
  import { plainToClass } from 'class-transformer';
  import { Observable } from 'rxjs';
  import { map } from 'rxjs/operators';
  
  interface ClassConstructor {
    new (...args: any[]): {};
  }
  
  export function OrderSerializer(dto: ClassConstructor) {
    return UseInterceptors(new OrderSerializerInterceptor(dto));
  }
  
  export class OrderSerializerInterceptor implements NestInterceptor {
    constructor(private dto: ClassConstructor) {}
  
    intercept(context: ExecutionContext, handler: CallHandler): Observable<any> {
      return handler.handle().pipe(
        map((data: any) => {
          const { orders, total } = data;
          const transformedOrders = orders.map((bag: any) => {
            return plainToClass(this.dto, bag, {
              excludeExtraneousValues: true,
            });
          });
  
          return { total, orders: transformedOrders };
        }),
      );
    }
  }
  