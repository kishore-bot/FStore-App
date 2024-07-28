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
export function FavoriteSerializer(dto: ClassConstructor) {
  return UseInterceptors(new FavoriteSerializerInterceptor(dto));
}

export class FavoriteSerializerInterceptor implements NestInterceptor {
  constructor(private dto: any) {}

  intercept(context: ExecutionContext, handler: CallHandler): Observable<any> {
    return handler.handle().pipe(
      map((data: any) => {
        const { favorites, total } = data; // Assuming 'products' and 'total' are properties in your response

        const transformedProducts = favorites.map((product: any) => {
          return plainToClass(this.dto, product, {
            excludeExtraneousValues: true,
          });
        });

        return { total, favorites: transformedProducts };
      }),
    );
  }
}
