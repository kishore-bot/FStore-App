"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.ReviewService = void 0;
const common_1 = require("@nestjs/common");
const typeorm_1 = require("@nestjs/typeorm");
const review_entity_1 = require("./entity/review.entity");
const typeorm_2 = require("typeorm");
const product_service_1 = require("../product/product.service");
let ReviewService = class ReviewService {
    constructor(reviewRepo, productService) {
        this.reviewRepo = reviewRepo;
        this.productService = productService;
    }
    async createReview(createReviewDto, user) {
        const product = await this.productService.productForReview(createReviewDto.productId, createReviewDto.rating);
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
    async fetchReview(productId, page = 1) {
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
    async fetchRating(productId) {
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
        const starCounts = reviews.reduce((acc, review) => {
            const rating = review.rating;
            if (rating >= 1 && rating <= 5) {
                acc[`star${rating}`] = (acc[`star${rating}`] || 0) + 1;
            }
            return acc;
        }, { star1: 0, star2: 0, star3: 0, star4: 0, star5: 0 });
        return {
            productRating,
            totalRating,
            starCounts,
        };
    }
};
exports.ReviewService = ReviewService;
exports.ReviewService = ReviewService = __decorate([
    (0, common_1.Injectable)(),
    __param(0, (0, typeorm_1.InjectRepository)(review_entity_1.Review)),
    __metadata("design:paramtypes", [typeorm_2.Repository,
        product_service_1.ProductService])
], ReviewService);
//# sourceMappingURL=review.service.js.map