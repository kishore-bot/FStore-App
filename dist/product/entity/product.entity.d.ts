import { Color } from './color.entity';
import { Size } from './size.entity';
import { Bag } from 'src/bag/entity/bag.entity';
import { Favorite } from 'src/favorite/entity/favourite.entity';
import { Seller } from 'src/seller/entity/seller.entitty';
import { ProductOrder } from 'src/order/entity/product_order.enity';
export declare class Product {
    id: number;
    category: string;
    price: number;
    description: string;
    mainCategory: string;
    popularity: number;
    discount: number;
    availability: number;
    gender: string;
    rating: number;
    createdAt: Date;
    updatedAt: Date;
    colors: Color[];
    sizes: Size[];
    seller: Seller;
    favorites: Favorite[];
    bagItems: Bag[];
    productOrders: ProductOrder[];
}
