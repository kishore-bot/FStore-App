import { BadRequestException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Orders } from './entity/order.entity';
import { Repository } from 'typeorm';
import { ProductOrder } from './entity/product_order.enity';
import { BagService } from 'src/bag/bag.service';
import { User } from 'src/user/entity/user.entitty';
import { UserService } from 'src/user/user.service';
import { ProductService } from 'src/product/product.service';
import { AwsService } from 'src/aws/aws.service';

@Injectable()
export class OrderService {
  constructor(
    @InjectRepository(Orders)
    private readonly orderRepo: Repository<Orders>,

    @InjectRepository(ProductOrder)
    private readonly productOrderRepo: Repository<ProductOrder>,

    private readonly bagsService: BagService,
    private readonly userService: UserService,
    private readonly productService: ProductService,
    private readonly awsService:AwsService
  ) {}

  generateOrderId(): string {
    const randomNumber = Math.floor(1000000 + Math.random() * 9000000);
    return randomNumber.toString();
  }

  generateRandomString(length: number = 12): string {
    const charset =
      'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let result = '';
    const charsetLength = charset.length;
    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * charsetLength);
      result += charset[randomIndex];
    }
    return result;
  }

  async placeOrders(user: User) {
    const address = await this.userService.fetchPrimaryAddress(user);
    const bags = await this.bagsService.fetchBagsForOrders(user);
    if (bags.length === 0) {
      throw new BadRequestException('No items found in bags');
    }
    const order = this.orderRepo.create({
      orderNo: this.generateOrderId(),
      orderId: this.generateRandomString(),
      totalPrice: 0,
      address: address,
      user: user,
    });
    await this.orderRepo.save(order);
    const productOrdersPromises = bags.map(async (bag) => {
      const { product, quantityNo } = await this.productService.productForOrder(
        bag.product.id,
        bag.quantity,
      );
      return this.productOrderRepo.create({
        quantity: quantityNo,
        price: product.price,
        color: bag.color,
        size: bag.size,
        order: order,
        product: product,
      });
    });
    const productOrders = await Promise.all(productOrdersPromises);
    await this.productOrderRepo.save(productOrders);
    const totalPrice = productOrders.reduce(
      (sum, po) => sum + po.price * po.quantity,
      0,
    );
    order.totalPrice = totalPrice;
    await this.orderRepo.save(order);
    await this.bagsService.clearBagsForOrders(user);
    return { message: 'Order Placed SuccessFully' };
  }

  async fetchOrders(user: User, page: number = 1) {
    const [orders, total] = await this.orderRepo
      .createQueryBuilder('order')
      .leftJoinAndSelect('order.user', 'user')
      .loadRelationCountAndMap(
        'order.productOrdersCount',
        'order.productOrders',
      )
      .where('user.id = :userId', { userId: user.id })
      .orderBy('order.createdAt', 'DESC')
      .skip((page - 1) * 5)
      .take(5)
      .getManyAndCount();

    return { total, orders };
  }

  async fetchOrderDetails(user: User, orderId: number) {
    const order = await this.orderRepo
      .createQueryBuilder('order')
      .leftJoinAndSelect('order.user', 'user')
      .leftJoinAndSelect('order.address', 'address')
      .leftJoinAndSelect('order.productOrders', 'productOrder')
      .leftJoinAndSelect('productOrder.product', 'product')
      .leftJoinAndSelect('product.seller', 'seller')
      .loadRelationCountAndMap('order.productOrdersCount', 'order.productOrders')
      .where('order.id = :orderId', { orderId })
      .andWhere('user.id = :userId', { userId: user.id })
      .getOne();
  
    const processedProductOrders = await Promise.all(
      order.productOrders.map(async (productOrder) => {
        const product = productOrder.product;
        const thumbnailUrl = product.thumbnail
          ? await this.awsService.getObjectUrl(product.thumbnail)
          : null;
        
        return {
          ...productOrder,
          product: {
            ...product,
            thumbnailUrl,
          },
        };
      })
    );
  
    return { ...order, productOrders: processedProductOrders };
  }
  

  // async fetchOrderDetails(user: User, orderId: number) {
  //   const order = await this.orderRepo
  //     .createQueryBuilder('order')
  //     .leftJoinAndSelect('order.user', 'user')
  //     .leftJoinAndSelect('order.address', 'address')
  //     .leftJoinAndSelect('order.productOrders', 'productOrder')
  //     .leftJoinAndSelect('productOrder.product', 'product')
  //     .leftJoinAndSelect('product.seller', 'seller')
  //     .loadRelationCountAndMap(
  //       'order.productOrdersCount',
  //       'order.productOrders',
  //     )
  //     .where('order.id = :orderId', { orderId })
  //     .andWhere('user.id = :userId', { userId: user.id })
  //     .getOne();

  //   const thumbnailsUrl=order.productOrders.product.thumbnailsUrl
  //   return order;
  // }

  async isProductOrdered(productId: number, user: User) {
    const orders = await this.orderRepo
      .createQueryBuilder('order')
      .leftJoinAndSelect('order.productOrders', 'productOrder')
      .leftJoinAndSelect('productOrder.product', 'product')
      .where('order.user.id = :userId', { userId: user.id })
      .getMany();
  
    const isOrdered = orders.some((order) =>
      order.productOrders.some((productOrder) => {
        return productOrder.product.id.toString() === productId.toString();
      })
    );
  
    return { isOrdered };
  }
  
}
