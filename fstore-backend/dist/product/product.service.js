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
exports.ProductService = void 0;
const common_1 = require("@nestjs/common");
const typeorm_1 = require("@nestjs/typeorm");
const product_entity_1 = require("./entity/product.entity");
const typeorm_2 = require("typeorm");
const color_service_1 = require("./color/color.service");
const size_service_1 = require("./size/size.service");
const aws_service_1 = require("../aws/aws.service");
let ProductService = class ProductService {
    constructor(productRepo, colorService, sizeService, awsService) {
        this.productRepo = productRepo;
        this.colorService = colorService;
        this.sizeService = sizeService;
        this.awsService = awsService;
    }
    async create(productDto, seller) {
        const colors = await this.colorService.createColor(productDto.color);
        const sizes = await this.sizeService.createSize(productDto.size);
        const product = this.productRepo.create({
            ...productDto,
            colors,
            sizes,
            seller: seller,
        });
        await this.productRepo.save(product);
        return product;
    }
    async update(id, productDto, seller) {
        const product = await this.productRepo.findOne({
            where: { id },
            relations: ['seller'],
        });
        if (!product) {
            throw new common_1.NotFoundException(`Product with id ${id} not found`);
        }
        if (product.seller.id !== seller.id) {
            throw new common_1.BadRequestException(`You are not allowed to update this product`);
        }
        const updatedProduct = this.productRepo.merge(product, productDto);
        await this.productRepo.save(updatedProduct);
        return updatedProduct;
    }
    async fetchCategories(query) {
        const categories = await this.productRepo
            .createQueryBuilder('product')
            .select('DISTINCT product.category', 'category')
            .where('product.mainCategory = :mainCategory', {
            mainCategory: query.mainCategory,
        })
            .andWhere('product.gender = :gender', { gender: query.gender })
            .orderBy('category', 'ASC')
            .getRawMany();
        const uniqueCategories = categories.map((category) => category.category);
        return uniqueCategories;
    }
    async fetchBrands(mainCategory) {
        const q = mainCategory.toLowerCase();
        const products = await this.productRepo.find({
            where: { mainCategory: q },
            relations: ['seller'],
        });
        const sellersMap = new Map();
        products.forEach((product) => {
            sellersMap.set(product.seller.id, product.seller);
        });
        const uniqueSellers = Array.from(sellersMap.values());
        uniqueSellers.sort((a, b) => a.name.localeCompare(b.name));
        return uniqueSellers;
    }
    async fetchProductDetails(productId, user) {
        const product = await this.productRepo.findOne({
            where: { id: productId },
            relations: ['seller', 'favorites', 'favorites.user', 'colors', 'sizes', 'images'],
        });
        if (!product) {
            throw new common_1.NotFoundException('Product not found');
        }
        const isFav = product.favorites.some((favorite) => favorite.user.id === user.id);
        product.favorite = isFav;
        const rate = product.discount;
        if (rate !== 0) {
            const price = product.price;
            const discount = 1 - rate / 100;
            product.price = price * discount;
        }
        const colorNames = product.colors.map((color) => color.name);
        const sizeNames = product.sizes.map((size) => size.name);
        product.color = colorNames;
        product.size = sizeNames;
        const imageUrls = await Promise.all(product.images.map(async (image) => {
            const url = await this.awsService.getObjectUrl(image.key);
            return url;
        }));
        product.imageUrls = imageUrls;
        return product;
    }
    async fetchByQuery(query, user) {
        const queryBuilder = this.productRepo
            .createQueryBuilder('product')
            .leftJoinAndSelect('product.seller', 'seller')
            .leftJoinAndSelect('product.favorites', 'favorites', 'favorites.userId = :userId', { userId: user.id })
            .leftJoinAndSelect('product.colors', 'color')
            .leftJoinAndSelect('product.sizes', 'size');
        if (query.mainCategory) {
            queryBuilder.andWhere('product.mainCategory = :mainCategory', {
                mainCategory: query.mainCategory,
            });
        }
        if (query.category) {
            queryBuilder.andWhere('product.category = :category', {
                category: query.category,
            });
        }
        if (query.byNew !== 0) {
            queryBuilder.addOrderBy('product.createdAt', 'DESC');
        }
        if (query.byPopular !== 0) {
            queryBuilder.addOrderBy('product.popularity', 'DESC');
        }
        if (query.byRating !== 0) {
            queryBuilder.addOrderBy('product.rating', 'DESC');
        }
        if (query.lowPrice > 0 &&
            query.highPrice > 0 &&
            query.highPrice > query.lowPrice) {
            queryBuilder.andWhere('product.price BETWEEN :lowPrice AND :highPrice', {
                lowPrice: query.lowPrice,
                highPrice: query.highPrice,
            });
        }
        if (query.byPriceSort) {
            if (query.byPriceSort === 1) {
                queryBuilder.addOrderBy('product.price', 'ASC');
            }
            if (query.byPriceSort === 2) {
                queryBuilder.addOrderBy('product.price', 'DESC');
            }
        }
        if (query.available !== undefined) {
            if (query.available === 1) {
                queryBuilder.andWhere('product.availability > 0');
            }
            else {
                queryBuilder.andWhere('product.availability >= 0');
            }
        }
        if (query.gender) {
            queryBuilder.andWhere('product.gender = :gender', {
                gender: query.gender,
            });
        }
        if (query.color) {
            queryBuilder
                .innerJoin('product.colors', 'colorFilter')
                .andWhere('colorFilter.name = :color', { color: query.color });
        }
        if (query.size) {
            queryBuilder
                .innerJoin('product.sizes', 'sizeFilter')
                .andWhere('sizeFilter.name = :size', { size: query.size });
        }
        if (query.name && query.name.length > 0) {
            const names = Array.isArray(query.name) ? query.name : [query.name];
            queryBuilder.andWhere('seller.name IN (:...names)', { names });
        }
        const total = await queryBuilder.getCount();
        const offset = (query.page - 1) * 5;
        queryBuilder.skip(offset).take(5);
        const products = await queryBuilder.getMany();
        const processedProducts = await Promise.all(products.map(async (product) => {
            const thumbnailUrl = product.thumbnail ? await this.awsService.getObjectUrl(product.thumbnail) : null;
            return {
                ...product,
                isFavorite: product.favorites && product.favorites.length > 0,
                thumbnailUrl,
            };
        }));
        return { total, products: processedProducts };
    }
    async productForOrder(productId, quantityNo) {
        const product = await this.productRepo.findOneBy({ id: productId });
        if (!product) {
            throw new Error('Product not found');
        }
        let adjustedQuantityNo = quantityNo;
        let newAvailability = product.availability - quantityNo;
        if (newAvailability < 0) {
            adjustedQuantityNo = product.availability;
            newAvailability = 0;
        }
        product.availability = newAvailability;
        product.popularity += 1;
        await this.productRepo.save(product);
        return { product, quantityNo: adjustedQuantityNo };
    }
    async fetchProduct(productId) {
        const product = await this.productRepo.findOne({
            where: { id: productId },
        });
        return product;
    }
    async productForReview(productId, rating) {
        const product = await this.productRepo.findOneBy({ id: productId });
        if (!product) {
            throw new Error('Product not found');
        }
        if (product.noOfRating === 0) {
            product.noOfRating = 1;
            product.rating = rating;
        }
        else {
            product.noOfRating += 1;
            product.rating =
                (product.rating * (product.noOfRating - 1) + rating) /
                    product.noOfRating;
        }
        await this.productRepo.save(product);
        return product;
    }
    async forProductImages(productId, date) {
        const product = await this.productRepo.findOne({
            where: { id: productId },
        });
        product.thumbnail = `product/${product.id.toString()}/m${1}_${date}`;
        await this.productRepo.save(product);
        return product;
    }
};
exports.ProductService = ProductService;
exports.ProductService = ProductService = __decorate([
    (0, common_1.Injectable)(),
    __param(0, (0, typeorm_1.InjectRepository)(product_entity_1.Product)),
    __metadata("design:paramtypes", [typeorm_2.Repository,
        color_service_1.ColorService,
        size_service_1.SizeService,
        aws_service_1.AwsService])
], ProductService);
//# sourceMappingURL=product.service.js.map