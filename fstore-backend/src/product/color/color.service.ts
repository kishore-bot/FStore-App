import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Color } from '../entity/color.entity';
import { Repository } from 'typeorm';

@Injectable()
export class ColorService {
  constructor(
    @InjectRepository(Color)
    private readonly colorRepo: Repository<Color>,
  ) {}

  async createColor(colorDto: string[]) {
    const colors = await Promise.all(
      colorDto.map(async (colorName) => {
        let color = await this.colorRepo.findOne({ where: { name: colorName } });
        if (!color) {
          color = this.colorRepo.create({ name: colorName });
          await this.colorRepo.save(color);
        }
        return color;
      }),
    );

    return colors; // Return the array of colors
  }
}

