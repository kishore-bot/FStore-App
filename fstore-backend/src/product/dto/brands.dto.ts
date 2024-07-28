import { Expose, Transform } from 'class-transformer';

export class BrandsDto {
  @Expose()
  id: number;
  @Expose()
  name: string;
}
