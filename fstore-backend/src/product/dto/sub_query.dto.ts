import { Transform } from 'class-transformer';
import { IsEnum, IsOptional, IsString } from 'class-validator';

enum Gender {
  MALE = 'male',
  FEMALE = 'female',
  UNISEX = 'unisex',
  KIDS = 'kids',
}

export class SubQueryDto {
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
  mainCategory?: string;
}
