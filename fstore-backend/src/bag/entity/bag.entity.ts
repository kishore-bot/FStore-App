
import { Product } from 'src/product/entity/product.entity';
import { User } from 'src/user/entity/user.entitty';
import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  CreateDateColumn,
} from 'typeorm';

@Entity()
export class Bag {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({
    name: 'quantity',
    type: 'int',
  })
  quantity: number;

  @Column({
    name: 'price',
    type: 'float',
  })
  price: number;

  @Column({
    name: 'color',
    type: 'varchar',
  })
  color: string;

  @Column({
    name: 'size',
    type: 'varchar',
  })
  size: string;

  @ManyToOne(() => Product, (product) => product.bagItems)
  product: Product;

  @ManyToOne(() => User, (user) => user.bags)
  user: User;

  @CreateDateColumn({
    name: 'created_at',
    type: 'timestamp',
    default: () => 'CURRENT_TIMESTAMP',
  })
  createdAt: Date;
}
