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
  
  export function ReviewSerializer(dto: ClassConstructor) {
    return UseInterceptors(new ReviewSerializerInterceptor(dto));
  }
  
  export class ReviewSerializerInterceptor implements NestInterceptor {
    constructor(private dto: ClassConstructor) {}
  
    intercept(context: ExecutionContext, handler: CallHandler): Observable<any> {
      return handler.handle().pipe(
        map((data: any) => {
          const { review, total } = data;
          const transformedreviews = review.map((bag: any) => {
            return plainToClass(this.dto, bag, {
              excludeExtraneousValues: true,
            });
          });
  
          return { total, review: transformedreviews };
        }),
      );
    }
  }
  