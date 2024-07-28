import { BagService } from './bag.service';
import { User } from 'src/user/entity/user.entitty';
import { UpdateQuantityDto } from './dto/update_qunatity.dto';
import { CreateBagDto } from './dto/create_bag.dto';
export declare class BagController {
    private readonly bagService;
    constructor(bagService: BagService);
    createBag(bag: CreateBagDto, user: User): Promise<{
        message: string;
    }>;
    fetchTotalPrice(user: User): Promise<{
        price: number;
    }>;
    fetchBags(page: number, user: User): Promise<{
        bags: import("./entity/bag.entity").Bag[];
        total: number;
    }>;
    deleteBags(id: number): Promise<{
        message: string;
    }>;
    updateQunatity(bag: UpdateQuantityDto): Promise<{
        message: string;
    }>;
}
