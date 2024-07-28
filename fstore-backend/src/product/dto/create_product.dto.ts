import { Transform } from 'class-transformer';
import { IsString, IsNumber, IsArray, IsEnum, Min } from 'class-validator';

enum Gender {
  MALE = 'male',
  FEMALE = 'female',
  UNISEX = 'unisex',
  KIDS = 'kids',
}

export class CreateProductDto {
  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  category: string;

  @IsNumber()
  @Min(0)
  price: number;

  @IsArray()
  @IsString({ each: true })
  @Transform(({ value }) => value.map((color: string) => color.toLowerCase()), { toClassOnly: true })
  color: string[];

  @IsString()
  description: string;

  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  mainCategory: string;

  @IsNumber()
  @Min(0)
  availability: number;

  @IsEnum(Gender)
  @Transform(({ value }) => value.toLowerCase())
  gender: Gender;

  @IsArray()
  @IsString({ each: true })
  @Transform(({ value }) => value.map((size: string) => size.toLowerCase()), { toClassOnly: true })
  size: string[];
}
