import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Review } from './entity/review.entity';
import { Repository } from 'typeorm';
import { ProductService } from 'src/product/product.service';
import { User } from 'src/user/entity/user.entitty';
import { CreateReviewDto } from './dto/create_review.dto';

@Injectable()
export class ReviewService {
  constructor(
    @InjectRepository(Review) private readonly reviewRepo: Repository<Review>,
    private readonly productService: ProductService,
  ) {}

  async createReview(createReviewDto: CreateReviewDto, user: User) {
    const product = await this.productService.productForReview(
      createReviewDto.productId,
      createReviewDto.rating,
    );
    if (!product) {
      throw new Error('Product not found');
    }

    const existingReview = await this.reviewRepo.findOne({
      where: {
        user: { id: user.id },
        product: { id: createReviewDto.productId },
      },
    });
    if (existingReview) {
      return { message: 'Review already exists' };
    }

    const review = this.reviewRepo.create({
      ...createReviewDto,
      user: user,
      product: product,
    });
    await this.reviewRepo.save(review);

    return { message: 'Review created successfully' };
  }

  async fetchReview(productId: number, page: number = 1) {
    const pageSize = 5;
    const skip = (page - 1) * pageSize;
    const [reviews, total] = await this.reviewRepo
      .createQueryBuilder('review')
      .leftJoinAndSelect('review.product', 'product')
      .leftJoinAndSelect('review.user', 'user')
      .where('review.product.id = :productId', { productId })
      .skip(skip)
      .take(pageSize)
      .getManyAndCount();
    return {
      review: reviews,
      total: total,
    };
  }

  async fetchRating(productId: number) {
    const reviews = await this.reviewRepo
      .createQueryBuilder('review')
      .leftJoinAndSelect('review.product', 'product')
      .where('review.product.id = :productId', { productId })
      .getMany();

    if (reviews.length === 0) {
      return {
        productRating: 0,
        totalRating: 0,
        starCounts: {
          star1: 0,
          star2: 0,
          star3: 0,
          star4: 0,
          star5: 0,
        },
      };
    }

    const productRating = Math.round(reviews[0].product.rating);
    const totalRating = reviews[0].product.noOfRating;

    const starCounts = reviews.reduce(
      (acc, review) => {
        const rating = review.rating;
        if (rating >= 1 && rating <= 5) {
          acc[`star${rating}`] = (acc[`star${rating}`] || 0) + 1;
        }
        return acc;
      },
      { star1: 0, star2: 0, star3: 0, star4: 0, star5: 0 },
    );

    return {
      productRating,
      totalRating,
      starCounts,
    };
  }
}
