import { IsNumber } from "class-validator";


export class ProductImagesDto{
    @IsNumber()
    id:number

    @IsNumber()
    noOfImages:number
}