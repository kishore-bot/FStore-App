import { Product } from "src/product/entity/product.entity";
import { Column, CreateDateColumn, Entity, ManyToMany, ManyToOne, PrimaryGeneratedColumn } from "typeorm";

@Entity()
export class ProductImages{
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  key: string;

  @CreateDateColumn({
    name: 'created_at',
    type: 'timestamp',
    default: () => 'CURRENT_TIMESTAMP',
  })
  createdAt: Date;

  @ManyToOne(() => Product, (product) => product.images)
  product: Product;
}
