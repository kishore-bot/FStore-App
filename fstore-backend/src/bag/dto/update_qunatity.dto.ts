import { IsNumber } from 'class-validator';

export class UpdateQuantityDto {
  @IsNumber()
  id: number;

  @IsNumber()
  quantity: number;
}
