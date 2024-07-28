import {
  Column,
  CreateDateColumn,
  Entity,
  ManyToOne,
  PrimaryGeneratedColumn,
} from 'typeorm';
import { Orders } from './order.entity';
import { Product } from 'src/product/entity/product.entity';

@Entity()
export class ProductOrder {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({
    name: 'quantity',
    type: 'int',
    nullable: false,
  })
  quantity: number;

  @Column({
    name: 'price',
    type: 'float',
    nullable: false,
  })
  price: number;
  @Column({
    name: 'color',
    type: 'varchar',
    nullable: false,
  })
  color: string;

  @Column({
    name: 'size',
    type: 'varchar',
    nullable: false,
  })
  size: string;

  @CreateDateColumn({
    name: 'created_at',
    type: 'timestamp',
    default: () => 'CURRENT_TIMESTAMP',
  })
  createdAt: Date;

  @ManyToOne(() => Orders, (order) => order.productOrders)
  order: Orders;

  @ManyToOne(() => Product, (product) => product.productOrders)
  product: Product;
}
