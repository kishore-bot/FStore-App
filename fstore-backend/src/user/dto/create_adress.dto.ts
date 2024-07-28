import { IsString, IsInt } from 'class-validator';
import { Transform } from 'class-transformer';

export class CreateAddressDto {

  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  name: string;

  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  address: string;

  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  city: string;

  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  state: string;

  @IsInt()
  pincode: number;

  @IsString()
  @Transform(({ value }) => value.toLowerCase())
  country: string;
}
