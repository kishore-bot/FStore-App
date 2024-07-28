import { Transform } from 'class-transformer';
import { IsNumber, IsOptional, IsString } from 'class-validator';

export class CreateBagDto {

  @IsNumber()
  productId: number;

  @IsNumber()
  @IsOptional()
  price: number = 0;

  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  color: string;

  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  size: string;
}
