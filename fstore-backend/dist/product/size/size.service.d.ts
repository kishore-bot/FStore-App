import { Size } from '../entity/size.entity';
import { Repository } from 'typeorm';
export declare class SizeService {
    private readonly sizeRepo;
    constructor(sizeRepo: Repository<Size>);
    createSize(sizeDto: string[]): Promise<Size[]>;
}
