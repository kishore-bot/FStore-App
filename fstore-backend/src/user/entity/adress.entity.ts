import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  OneToMany,
} from 'typeorm';
import { User } from './user.entitty';
import { Orders } from 'src/order/entity/order.entity';



@Entity()
export class Address {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({
    name: 'name',
    type: 'varchar',
    length: 20,
    nullable: false,
  })
  name: string;

  @Column({
    name: 'address',
    type: 'varchar',
    length: 50,
    nullable: false,
  })
  address: string;

  @Column({
    name: 'city',
    type: 'varchar',
    length: 20,
    nullable: false,
  })
  city: string;

  @Column({
    name: 'state',
    type: 'varchar',
    length: 20,
    nullable: false,
  })
  state: string;

  @Column({
    name: 'pincode',
    type: 'int',
    nullable: false,
  })
  pincode: number;

  @Column({
    name: 'country',
    type: 'varchar',
    length: 20,
    nullable: false,
  })
  country: string;

  @ManyToOne(() => User, (user) => user.addresses)
  user: User;

  @OneToMany(() => Orders, (order) => order.address)
  orders: Orders[];
}












// @Entity()
// export class Address {
//   @PrimaryGeneratedColumn()
//   id: number;

//   @Column({
//     name: 'name',
//     type: 'varchar',
//     length: 20,
//     nullable: false,
//   })
//   name: string;

//   @Column({
//     name: 'address',
//     type: 'varchar',
//     length: 50,
//     nullable: false,
//   })
//   address: string;

//   @Column({
//     name: 'city',
//     type: 'varchar',
//     length: 20,
//     nullable: false,
//   })
//   city: string;

//   @Column({
//     name: 'state',
//     type: 'varchar',
//     length: 20,
//     nullable: false,
//   })
//   state: string;

//   @Column({
//     name: 'pincode',
//     type: 'int',
//     nullable: false,
//   })
//   pincode: number;

//   @Column({
//     name: 'country',
//     type: 'varchar',
//     length: 20,
//     nullable: false,
//   })
//   country: string;

//   @ManyToOne(() => User, (user) => user.addresses)
//   user: User;
// }
