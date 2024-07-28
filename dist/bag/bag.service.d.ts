import { Bag } from './entity/bag.entity';
import { Repository } from 'typeorm';
import { User } from 'src/user/entity/user.entitty';
import { ProductService } from 'src/product/product.service';
import { CreateBagDto } from './dto/create_bag.dto';
import { UpdateQuantityDto } from './dto/update_qunatity.dto';
export declare class BagService {
    private readonly bagRepo;
    private readonly productService;
    constructor(bagRepo: Repository<Bag>, productService: ProductService);
    createBag(bagDto: CreateBagDto, user: User): Promise<{
        message: string;
    }>;
    fetchBags(page: number, user: User): Promise<{
        bags: Bag[];
        total: number;
    }>;
    getTotalPrice(user: User): Promise<{
        price: number;
    }>;
    deleteBags(uid: number): Promise<{
        message: string;
    }>;
    updateQuantity(quantity: UpdateQuantityDto): Promise<{
        message: string;
    }>;
    fetchBagsForOrders(user: User): Promise<Bag[]>;
    clearBagsForOrders(user: User): Promise<void>;
}
