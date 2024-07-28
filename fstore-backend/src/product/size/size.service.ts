import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Size } from '../entity/size.entity';
import { Repository } from 'typeorm';

@Injectable()
export class SizeService {
  constructor(
    @InjectRepository(Size)
    private readonly sizeRepo: Repository<Size>,
  ) {}

  async createSize(sizeDto: string[]) {
    const sizes = await Promise.all(
      sizeDto.map(async (sizeName) => {
        let size = await this.sizeRepo.findOne({ where: { name: sizeName } });
        if (!size) {
          size = this.sizeRepo.create({ name: sizeName });
          await this.sizeRepo.save(size);
        }
        return size;
      }),
    );

    return sizes;
  }
}
