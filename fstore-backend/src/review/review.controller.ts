import { Body, Controller, Get, Post, Query, UseGuards } from '@nestjs/common';
import { CreateReviewDto } from './dto/create_review.dto';
import { GetUser } from 'src/auth/decorator/get.user.decorator';
import { User } from 'src/user/entity/user.entitty';
import { ReviewService } from './review.service';
import { JwtUserGuard } from 'src/auth/guard/jtw.user.guard';
import { Serialize } from 'src/interceptor/serialize.interceptor';
import { FetchReviewsDto } from './dto/fetch_review.dto';
import { ReviewSerializer } from 'src/interceptor/review.serializer';

@Controller('review')
export class ReviewController {
  constructor(private readonly reviewService: ReviewService) {}

  @Get('/rating')
  @UseGuards(JwtUserGuard)
  fetchRating(@Query('id') id: string) {
    const productId = parseInt(id, 10);
    return this.reviewService.fetchRating(productId);
  }

  @Post()
  @UseGuards(JwtUserGuard)
  createProduct(@Body() createReview: CreateReviewDto, @GetUser() user: User) {
    return this.reviewService.createReview(createReview, user);
  }

  @Get()
  @ReviewSerializer(FetchReviewsDto)
  @UseGuards(JwtUserGuard)
  fetchReviews(@Query('id') id: string, @Query('page') pageNo: string) {
    const productId = parseInt(id, 10);
    const page = parseInt(pageNo, 10);
    return this.reviewService.fetchReview(productId,page);
  }
}
