import { Controller, Get, Param, Post, UseGuards } from '@nestjs/common';
import { OrderService } from './order.service';
import { JwtUserGuard } from 'src/auth/guard/jtw.user.guard';
import { GetUser } from 'src/auth/decorator/get.user.decorator';
import { User } from 'src/user/entity/user.entitty';
import { OrderSerializer } from 'src/interceptor/order.serializer';
import { OrdersDto } from './dto/orders.dto';
import { OrderByDto } from './dto/orders_by.dto';
import { Serialize } from 'src/interceptor/serialize.interceptor';

@Controller('order')
export class OrderController {
  constructor(private readonly orderService: OrderService) {}

  @Get('/isOrdered/:id')
  @UseGuards(JwtUserGuard)
  async isOrdered(@GetUser() user: User, @Param('id') id: number) {
    return await this.orderService.isProductOrdered(id, user);
  }

  @Get('/by/:id')
  @Serialize(OrderByDto)
  @UseGuards(JwtUserGuard)
  async fetchById(@GetUser() user: User, @Param('id') id: number) {
    const order = await this.orderService.fetchOrderDetails(user, id);
    return order;
  }

  @Post()
  @UseGuards(JwtUserGuard)
  async placeOrder(@GetUser() user: User) {
    const order = await this.orderService.placeOrders(user);
    return order;
  }

  @Get('/:page')
  @UseGuards(JwtUserGuard)
  @OrderSerializer(OrdersDto)
  async fetchOrder(@GetUser() user: User, @Param('page') page: number) {
    const order = await this.orderService.fetchOrders(user, page);
    return order;
  }
}
