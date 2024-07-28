import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  ManyToMany,
  ManyToOne,
  OneToMany,
  JoinTable,
} from 'typeorm';
import { Color } from './color.entity';
import { Size } from './size.entity';
import { Bag } from 'src/bag/entity/bag.entity';
import { Favorite } from 'src/favorite/entity/favourite.entity';
import { Seller } from 'src/seller/entity/seller.entitty';
import { ProductOrder } from 'src/order/entity/product_order.enity';
import { Review } from 'src/review/entity/review.entity';
import { ProductImages } from 'src/image/entity/product_image.entity';

@Entity()
export class Product {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ name: 'category', type: 'varchar', length: 15, nullable: false })
  category: string;

  @Column({ name: 'price', type: 'float', nullable: false })
  price: number;

  @Column({ name: 'description', type: 'varchar', nullable: false })
  description: string;

  @Column({
    name: 'mainCategory',
    type: 'enum',
    enum: ['clothes', 'shoes', 'accessories', 'cosmetics'],
    nullable: false,
  })
  mainCategory: string;

  @Column({ name: 'popularity', type: 'int', default: 0 })
  popularity: number;

  @Column({ name: 'discount', type: 'float', default: 0 })
  discount: number;

  @Column({ name: 'availability', type: 'int', nullable: false, default: 0 })
  availability: number;

  @Column({
    name: 'gender',
    type: 'enum',
    enum: ['male', 'female', 'unisex', 'kids'],
    nullable: false,
  })
  gender: string;

  @Column({ name: 'rating', type: 'float', default: 0 })
  rating: number;

  @Column({ name: 'noOfRating', type: 'float', default: 0 })
  noOfRating: number;

  @Column({ name: 'thumbnail', type: 'varchar', default: '', nullable: true })
  thumbnail: string;

  @CreateDateColumn({
    name: 'created_at',
    type: 'timestamp',
    default: () => 'CURRENT_TIMESTAMP',
  })
  createdAt: Date;

  @UpdateDateColumn({
    name: 'updated_at',
    type: 'timestamp',
    default: () => 'CURRENT_TIMESTAMP',
    onUpdate: 'CURRENT_TIMESTAMP',
  })
  updatedAt: Date;

  @ManyToMany(() => Color, (color) => color.products)
  @JoinTable()
  colors: Color[];

  @ManyToMany(() => Size, (size) => size.products)
  @JoinTable()
  sizes: Size[];

  @ManyToOne(() => Seller, (seller) => seller.products)
  seller: Seller;

  @OneToMany(() => Favorite, (favorite) => favorite.product)
  favorites: Favorite[];

  @OneToMany(() => Bag, (bag) => bag.product)
  bagItems: Bag[];

  @OneToMany(() => ProductOrder, (productOrder) => productOrder.product)
  productOrders: ProductOrder[];

  @OneToMany(() => Review, (review) => review.product)
  reviews: Review[];

  @OneToMany(() => ProductImages, (images) => images.product)
  images: ProductImages[];
}
