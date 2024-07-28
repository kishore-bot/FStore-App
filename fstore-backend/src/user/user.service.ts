import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { User } from './entity/user.entitty';
import { Address } from './entity/adress.entity';
import { CreateAddressDto } from './dto/create_adress.dto';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(User) private readonly userRepo: Repository<User>,
    @InjectRepository(Address)
    private readonly addressRepo: Repository<Address>,
  ) {}

  async createAddress(createAddressDto: CreateAddressDto, user: User) {
    const address = this.addressRepo.create({
      ...createAddressDto,
      user: user,
    });
    await this.addressRepo.save(address);
    if (user.primaryAddress == 0) {
      user.primaryAddress = address.id;
      await this.userRepo.save(user);
    }
    return { message: 'Created Address' };
  }

  async fetchAddress(user: User) {
    const addresses = await this.addressRepo.find({
      where: { user: user },
    });

    return {
      addresses: addresses.map((address) => ({
        id: address.id,
        name: address.name,
        address: address.address,
        city: address.city,
        state: address.state,
        pincode: address.pincode,
        country: address.country,
      })),
      primaryAddress: user.primaryAddress,
    };
  }

  async deleteAddress(user: User, addressId: number) {
    const address = await this.addressRepo.findOne({
      where: { id: addressId },
    });
    if (address.id === user.primaryAddress) {
      user.primaryAddress = 0;
      await this.userRepo.save(user);
    }
    await this.addressRepo.remove(address);
    return { message: 'Address Deleted' };
  }

  async fetchPrimaryAddress(user: User) {
    const address = await this.addressRepo.findOneBy({
      id: user.primaryAddress,
    });
    if (address == null) {
      return {
        address: null,
        city: null,
        country: null,
        id: null,
        name: null,
        pincode: null,
        state: null,
      };
    }
    return address;
  }
  

  async changePrimaryAddress(adddressId: number, user: User) {
    const address = await this.addressRepo.findOneBy({ id: adddressId });
    if (!address) {
      return { message: 'No Address Found' };
    }
    user.primaryAddress = address.id;
    await this.userRepo.save(user);
    return { message: 'Changes Address' };
  }
}
