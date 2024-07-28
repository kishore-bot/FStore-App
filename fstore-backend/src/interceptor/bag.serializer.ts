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

export function BagSerializer(dto: ClassConstructor) {
  return UseInterceptors(new BagSerializerInterceptor(dto));
}

export class BagSerializerInterceptor implements NestInterceptor {
  constructor(private dto: ClassConstructor) {}

  intercept(context: ExecutionContext, handler: CallHandler): Observable<any> {
    return handler.handle().pipe(
      map((data: any) => {
        const { bags, total } = data;
        const transformedBags = bags.map((bag: any) => {
          return plainToClass(this.dto, bag, {
            excludeExtraneousValues: true,
          });
        });

        return { total, bags: transformedBags };
      }),
    );
  }
}
