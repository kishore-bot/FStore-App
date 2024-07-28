import { Address } from 'src/user/entity/adress.entity';
import {
  Column,
  CreateDateColumn,
  Entity,
  ManyToOne,
  OneToMany,
  PrimaryGeneratedColumn,
  UpdateDateColumn,
} from 'typeorm';
import { ProductOrder } from './product_order.enity';
import { User } from 'src/user/entity/user.entitty';

@Entity()
export class Orders {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({
    name: 'orderNo',
    type: 'varchar',
    length: 20,
    unique: true,
  })
  orderNo: string;


  @Column({
    name: 'orderId',
    type: 'varchar',
    length: 20,
    unique: true,
  })
  orderId: string;

  @Column({
    name: 'totalPrice',
    type: 'float',
    nullable: false,
  })
  totalPrice: number;

  @Column({
    name: 'status',
    type: 'enum',
    enum: ['delivered', 'pending', 'cancelled'],
    default: 'pending',
  })
  status: string;

  @CreateDateColumn({
    name: 'created_at',
    type: 'timestamp',
    default: () => 'CURRENT_TIMESTAMP',
  })
  createdAt: Date;

  @ManyToOne(() => Address, (address) => address.orders)
  address: Address;

  @OneToMany(() => ProductOrder, (productOrder) => productOrder.order)
  productOrders: ProductOrder[];

  @ManyToOne(() => User, (user) => user.orders, {})
  user: User;
}
