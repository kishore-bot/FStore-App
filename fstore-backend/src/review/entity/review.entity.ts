import { Product } from 'src/product/entity/product.entity';
import { User } from 'src/user/entity/user.entitty';
import {
  Column,
  CreateDateColumn,
  Entity,
  ManyToOne,
  PrimaryGeneratedColumn,
} from 'typeorm';

@Entity()
export class Review {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ name: 'comment', type: 'varchar', length: 15, nullable: true })
  comment: string;

  @Column({ name: 'rating', type: 'float', default: 0, nullable: false })
  rating: number;

  @CreateDateColumn({
    name: 'created_at',
    type: 'timestamp',
    default: () => 'CURRENT_TIMESTAMP',
  })
  createdAt: Date;

  @ManyToOne(() => Product, (product) => product.reviews)
  product: Product;

  @ManyToOne(() => User, (user) => user.reviews)
  user: User;
}
