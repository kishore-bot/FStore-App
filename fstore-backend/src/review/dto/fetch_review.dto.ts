import { Optional } from '@nestjs/common';
import { Expose, Transform } from 'class-transformer';

export class FetchReviewsDto {
  @Expose()
  @Optional()
  id: number;

  @Expose()
  @Optional()
  @Transform(({ obj }) => obj.user.name)
  user: string;

  @Expose()
  @Optional()
  createdAt: string;

  @Expose()
  @Optional()
  comment: string;

  @Expose()
  @Optional()
  rating: string;
}
