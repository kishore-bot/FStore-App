import { Transform } from 'class-transformer';
import { IsString, IsNumber, IsArray, IsEnum, IsOptional, Min } from 'class-validator';

enum Gender {
  MALE = 'male',
  FEMALE = 'female',
  UNISEX = 'unisex',
  KIDS = 'kids',
}

export class UpdateProductDto {
  @IsOptional()
  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  category?: string;

  @IsOptional()
  @IsNumber()
  @Min(0)
  price?: number;

  @IsOptional()
  @IsArray()
  @IsString({ each: true })
  @Transform(({ value }) => value.map((color: string) => color.toLowerCase()), { toClassOnly: true })
  color?: string[];

  @IsOptional()
  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  description?: string;

  @IsOptional()
  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  mainCategory?: string;

  @IsOptional()
  @IsNumber()
  @Min(0)
  availability?: number;

  @IsOptional()
  @IsEnum(Gender)
  @Transform(({ value }) => value.toLowerCase())
  gender?: Gender;

  @IsOptional()
  @IsArray()
  @IsString({ each: true })
  @Transform(({ value }) => value.map((size: string) => size.toLowerCase()), { toClassOnly: true })
  size?: string[];

  @IsOptional()
  @IsNumber()
  @Min(0)
  discount?: number;
}
