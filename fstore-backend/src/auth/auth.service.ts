import {
  BadRequestException,
  HttpException,
  HttpStatus,
  Injectable,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { User } from 'src/user/entity/user.entitty';
import { Repository } from 'typeorm';
import { randomBytes, scrypt as _scrypt } from 'crypto';
import { promisify } from 'util';
import { CreateUser } from './dtos/create-user.dto';
import { JwtService } from '@nestjs/jwt';
import { Seller } from 'src/seller/entity/seller.entitty';
import { CreateSeller } from './dtos/create.seller.dto';

const scrypt = promisify(_scrypt);

@Injectable()
export class AuthService {
  constructor(
    @InjectRepository(User) private readonly userRepo: Repository<User>,
    @InjectRepository(Seller) private readonly sellerRepo: Repository<Seller>,
    private readonly jwtService: JwtService,
  ) {}

  async registerUser(attr: CreateUser) {
    try {
      const email = attr.email;
      const userEmail = await this.userRepo.findOne({ where: { email } });

      if (userEmail != null) {
        throw new HttpException(
          'Email already exists',
          HttpStatus.UNAUTHORIZED,
        );
      }

      const hashPass = await this.passwordHasher(attr.password);
      attr.password = hashPass;

      const user = this.userRepo.create(attr);
      await this.userRepo.save(user);

      const { password, name, ...userWithNoPass } = user;

      const token = await this.jwtService.signAsync(userWithNoPass);
      return { token };
    } catch (e) {
      throw new HttpException(e.message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  async registerSeller(attr: CreateSeller) {
    try {
      const email = attr.email;
      const admin = await this.sellerRepo.findOne({ where: { email } });

      if (admin != null) {
        throw new HttpException(
          'Email already exists',
          HttpStatus.UNAUTHORIZED,
        );
      }

      const hashPass = await this.passwordHasher(attr.password);
      attr.password = hashPass;

      const admins = this.sellerRepo.create(attr);
      await this.sellerRepo.save(admins);

      const { password, name, ...userWithNoPass } = admins;

      const token = await this.jwtService.signAsync(userWithNoPass);
      return { token };
    } catch (e) {
      throw new HttpException(e.message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  async validateUser(attr: CreateUser) {
    try {
      const email = attr.email;
      const userEmail = await this.userRepo.findOne({ where: { email } });

      if (userEmail == null) {
        throw new HttpException(
          'No account exist on this email',
          HttpStatus.UNAUTHORIZED,
        );
      }

      const [salt, storedHash] = userEmail.password.split('.');

      const hash = (await scrypt(attr.password, salt, 32)) as Buffer;

      if (storedHash !== hash.toString('hex')) {
        throw new BadRequestException('Invalid Password');
      }

      const { password, name, ...userWithNoPass } = userEmail;

      const token = await this.jwtService.signAsync(userWithNoPass);
      return { token };
    } catch (e) {
      throw new HttpException(e.message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  async validateSeller(attr: CreateSeller) {
    try {
      const email = attr.email;
      const userEmail = await this.sellerRepo.findOne({ where: { email } });

      if (userEmail == null) {
        throw new HttpException(
          'No account exist on this email',
          HttpStatus.UNAUTHORIZED,
        );
      }

      const [salt, storedHash] = userEmail.password.split('.');

      const hash = (await scrypt(attr.password, salt, 32)) as Buffer;

      if (storedHash !== hash.toString('hex')) {
        throw new BadRequestException('Invalid Password');
      }

      const { password, name, ...userWithNoPass } = userEmail;

      const token = await this.jwtService.signAsync(userWithNoPass);
      return { token };
    } catch (e) {
      throw new HttpException(e.message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  async passwordHasher(pass: string): Promise<string> {
    const salt = randomBytes(8).toString('hex');
    const hash = (await scrypt(pass, salt, 32)) as Buffer;
    const result = salt + '.' + hash.toString('hex');
    return result;
  }

  async verifyUser(id: string): Promise<User | null> {
    const userId = parseInt(id, 10);
    if (isNaN(userId)) {
      return null; // or throw an error if an invalid ID format should be handled
    }

    const user = await this.userRepo.findOne({ where: { id: userId } });
    return user || null;
  }

  async verifySeller(id: string){
    const userId = parseInt(id, 10);
    if (isNaN(userId)) {
      return null; // or throw an error if an invalid ID format should be handled
    }

    const user = await this.sellerRepo.findOne({ where: { id: userId } });
    return user || null;
  }
}
