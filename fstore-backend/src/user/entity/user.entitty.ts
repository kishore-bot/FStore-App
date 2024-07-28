import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  OneToMany,
} from 'typeorm';
import { Bag } from 'src/bag/entity/bag.entity';
import { Orders } from 'src/order/entity/order.entity';
import { Favorite } from 'src/favorite/entity/favourite.entity';
import { Address } from './adress.entity';
import { Review } from 'src/review/entity/review.entity';

@Entity()
export class User {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({
    name: 'name',
    type: 'varchar',
    length: 15,
    nullable: false,
  })
  name: string;

  @Column({
    name: 'email',
    type: 'varchar',
    length: 255,
    nullable: false,
  })
  email: string;

  @Column({
    name: 'password',
    nullable: false,
  })
  password: string;

  @Column({
    name: 'primary_address',
    type: 'int',
    default: 0,
  })
  primaryAddress: number;

  @OneToMany(() => Favorite, (favorite) => favorite.user)
  favorites: Favorite[];

  @OneToMany(() => Bag, (bag) => bag.user)
  bags: Bag[];

  @OneToMany(() => Address, (address) => address.user)
  addresses: Address[];

  @OneToMany(() => Orders, (order) => order.user)
  orders: Orders[];

  @OneToMany(() => Review, (review) => review.user)
  reviews: Review[];
}




























// import { Bag } from 'src/bag/entity/bag.entity';
// import { Favorite } from 'src/favorite/entity/favourite.entity';
// import { Entity, PrimaryGeneratedColumn, Column, OneToMany } from 'typeorm';
// import { Address } from './adress.entity';


// @Entity()
// export class User {
//   @PrimaryGeneratedColumn()
//   id: number;

//   @Column({
//     name: 'name',
//     type: 'varchar',
//     length: 15,
//     nullable: false,
//   })
//   name: string;

//   @Column({
//     name: 'email',
//     type: 'varchar',
//     length: 255,
//     nullable: false,
//   })
//   email: string;

//   @Column({
//     name: 'password',
//     nullable: false,
//   })
//   password: string;

//   @Column({
//     name: 'primary_address',
//     type: 'int',
//     default: 0,
//   })
//   primary_address: number;

//   @OneToMany(() => Favorite, (favorite) => favorite.user)
//   favorites: Favorite[];

//   @OneToMany(() => Bag, (bag) => bag.user)
//   bags: Bag[];

//   @OneToMany(() => Address, (address) => address.user)
//   addresses: Address[];
// }
