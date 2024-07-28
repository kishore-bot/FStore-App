import { AwsService } from 'src/aws/aws.service';
import { ProductImages } from './entity/product_image.entity';
import { Repository } from 'typeorm';
import { ProductService } from 'src/product/product.service';
import { ProductImagesDto } from './dto/product_image.dto';
export declare class ImageService {
    private readonly awsService;
    private readonly productImagesRepository;
    private readonly productService;
    constructor(awsService: AwsService, productImagesRepository: Repository<ProductImages>, productService: ProductService);
    createProductImage(body: ProductImagesDto): Promise<string[]>;
}
