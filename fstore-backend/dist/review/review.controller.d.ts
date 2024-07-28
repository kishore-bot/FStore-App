import { CreateReviewDto } from './dto/create_review.dto';
import { User } from 'src/user/entity/user.entitty';
import { ReviewService } from './review.service';
export declare class ReviewController {
    private readonly reviewService;
    constructor(reviewService: ReviewService);
    fetchRating(id: string): Promise<{
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
    createProduct(createReview: CreateReviewDto, user: User): Promise<{
        message: string;
    }>;
    fetchReviews(id: string, pageNo: string): Promise<{
        review: import("./entity/review.entity").Review[];
        total: number;
    }>;
}
