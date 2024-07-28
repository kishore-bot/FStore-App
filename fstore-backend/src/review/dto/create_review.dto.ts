import { Optional } from '@nestjs/common';
import { IsNumber, IsString } from 'class-validator';

export class CreateReviewDto {
  @IsString()
  @Optional()
  comment: string;

  @IsNumber()
  rating: number;

  @IsNumber()
  productId: number;
}
