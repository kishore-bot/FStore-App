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
export function FetchproductSerializer(dto: ClassConstructor) {
  return UseInterceptors(new FetchproductSerializerInterceptor(dto));
}

export class FetchproductSerializerInterceptor implements NestInterceptor {
  constructor(private dto: any) {}

  intercept(context: ExecutionContext, handler: CallHandler): Observable<any> {
    return handler.handle().pipe(
      map((data: any) => {
        const { products, total } = data; // Assuming 'products' and 'total' are properties in your response

        const transformedProducts = products.map((product: any) => {
          return plainToClass(this.dto, product, {
            excludeExtraneousValues: true,
          });
        });

        return { total, products: transformedProducts };
      }),
    );
  }
}
