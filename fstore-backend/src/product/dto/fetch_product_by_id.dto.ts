import { Expose, Transform } from 'class-transformer';

export class FetchProductById {

  @Expose()
  id: number;

  @Expose()
  price: number;

  @Expose()
  category: string;

  @Expose()
  mainCategory: string;

  @Expose()
  gender: string;

  @Expose()
  size: string[];

  @Expose()
  color: string[];

  @Expose()
  description: string;

  @Expose()
  popularity: number;

  @Expose()
  @Transform(({ obj }) => obj.availability > 0)
  available: boolean;

  @Expose()
  rating: number;

  @Expose()
  noOfRating: number;

  @Transform(({ obj }) => obj.seller.name)
  @Expose()
  name: string;

  @Expose()
  favorite: boolean;

  @Expose()
  @Transform(({ obj }) => obj.imageUrls)
  imageUrls :string
}
