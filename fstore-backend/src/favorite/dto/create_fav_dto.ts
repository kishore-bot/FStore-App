import { Transform } from 'class-transformer';
import { IsString, IsNumber } from 'class-validator';

export class CreateFavDto {

  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  color: string;

  @IsNumber()
  id: number;

  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  size: string;

}

