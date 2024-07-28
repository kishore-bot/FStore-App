import { Module } from '@nestjs/common';
import { BagService } from './bag.service';
import { BagController } from './bag.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Bag } from './entity/bag.entity';
import { ProductModule } from 'src/product/product.module';
import { AwsModule } from 'src/aws/aws.module';

@Module({
  imports: [TypeOrmModule.forFeature([Bag]),ProductModule,AwsModule],
  providers: [BagService],
  controllers: [BagController],
  exports:[BagService]
})
export class BagModule {}
