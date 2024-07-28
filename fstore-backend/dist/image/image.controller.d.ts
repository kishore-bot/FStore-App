import { ImageService } from './image.service';
import { ProductImagesDto } from './dto/product_image.dto';
export declare class ImageController {
    private readonly imageService;
    constructor(imageService: ImageService);
    getProductImageUrl(body: ProductImagesDto): Promise<{
        urls: string[];
    }>;
}
