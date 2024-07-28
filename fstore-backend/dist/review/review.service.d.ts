import { Review } from './entity/review.entity';
import { Repository } from 'typeorm';
import { ProductService } from 'src/product/product.service';
import { User } from 'src/user/entity/user.entitty';
import { CreateReviewDto } from './dto/create_review.dto';
export declare class ReviewService {
    private readonly reviewRepo;
    private readonly productService;
    constructor(reviewRepo: Repository<Review>, productService: ProductService);
    createReview(createReviewDto: CreateReviewDto, user: User): Promise<{
        message: string;
    }>;
    fetchReview(productId: number, page?: number): Promise<{
        review: Review[];
        total: number;
    }>;
    fetchRating(productId: number): Promise<{
        productRating: number;
        totalRating: number;
        starCounts: {
            star1: number;
            star2: number;
            star3: number;
            star4: number;
            star5: number;
        };
    }>;
}
