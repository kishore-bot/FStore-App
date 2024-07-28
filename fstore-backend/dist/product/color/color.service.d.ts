import { Color } from '../entity/color.entity';
import { Repository } from 'typeorm';
export declare class ColorService {
    private readonly colorRepo;
    constructor(colorRepo: Repository<Color>);
    createColor(colorDto: string[]): Promise<Color[]>;
}
