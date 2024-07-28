import { Product } from 'src/product/entity/product.entity';
import { User } from 'src/user/entity/user.entitty';
import {
  Entity,
  PrimaryGeneratedColumn,
  ManyToOne,
  CreateDateColumn,
  Column,
} from 'typeorm';

@Entity()
export class Favorite {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({
    name: 'size',
    type: 'varchar',
    length: 255,
    nullable: false,
  })
  size: string;

  @Column({
    name: 'color',
    type: 'varchar',
    length: 255,
    nullable: false,
  })
  color: string;

  @ManyToOne(() => User, (user) => user.favorites)
  user: User;

  @ManyToOne(() => Product, (product) => product.favorites, { nullable: false })
  product: Product;

  @CreateDateColumn({
    name: 'created_at',
    type: 'timestamp',
    default: () => 'CURRENT_TIMESTAMP',
  })
  createdAt: Date;
}
