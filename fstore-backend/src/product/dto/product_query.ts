import { Transform } from 'class-transformer';
import {
  IsArray,
  IsBoolean,
  IsEnum,
  IsNumber,
  IsOptional,
  IsString,
  Min,
} from 'class-validator';

enum Gender {
  MALE = 'male',
  FEMALE = 'female',
  UNISEX = 'unisex',
  KIDS = 'kids',
}

export class ProductQuery {
  @Transform(({ value }) => value.toLowerCase())
  @IsString()
  @IsOptional()
  category?: string;

  @Transform(({ value }) => parseInt(value, 10))
  @IsNumber()
  @IsOptional()
  @Min(0)
  byNew?: number = 0;

  @Transform(({ value }) => parseInt(value, 10))
  @IsNumber()
  @IsOptional()
  @Min(0)
  byPriceSort: number = 0;

  @Transform(({ value }) => parseInt(value, 10))
  @IsNumber()
  @IsOptional()
  @Min(0)
  byPopular?: number = 0;

  @Transform(({ value }) => parseInt(value, 10))
  @IsNumber()
  @IsOptional()
  @Min(0)
  byRating?: number = 0;

  @IsOptional()
  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  color?: string;

  @Transform(({ value }) => value.toLowerCase())
  @IsOptional()
  @IsString()
  mainCategory?: string;

  @Transform(({ value }) => parseInt(value, 10))
  @IsNumber()
  @IsOptional()
  available?: number;

  @Transform(({ value }) => {
    if (
      typeof value === 'string' &&
      Object.values(Gender).includes(value.toLowerCase() as Gender)
    ) {
      return value.toLowerCase() as Gender;
    }
    return undefined;
  })
  @IsOptional()
  @IsEnum(Gender)
  gender?: string;

  @Transform(({ value }) => value.toLowerCase())
  @IsOptional()
  @IsString()
  size?: string;

  @Transform(({ value }) => {
    if (Array.isArray(value)) {
      return value.map((v: string) => v.toLowerCase());
    } else if (typeof value === 'string') {
      return value.split(',').map((v: string) => v.trim().toLowerCase());
    } else {
      return [];
    }
  }, { toClassOnly: true })
  @IsArray()
  @IsOptional()
  @IsString({ each: true })
  name?: string[];

  @Transform(({ value }) => parseInt(value, 10))
  @IsOptional()
  @IsNumber()
  @Min(0)
  lowPrice: number = 0;

  @Transform(({ value }) => parseInt(value, 10))
  @IsOptional()
  @IsNumber()
  @Min(1)
  highPrice: number;

  @Transform(({ value }) => parseInt(value, 10))
  @IsNumber()
  @Min(0)
  page: number;
}
