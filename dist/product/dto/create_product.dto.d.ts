declare enum Gender {
    MALE = "male",
    FEMALE = "female",
    UNISEX = "unisex",
    KIDS = "kids"
}
export declare class CreateProductDto {
    category: string;
    price: number;
    color: string[];
    description: string;
    mainCategory: string;
    availability: number;
    gender: Gender;
    size: string[];
}
export {};
