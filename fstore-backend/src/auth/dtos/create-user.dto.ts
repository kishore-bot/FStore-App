import { Transform } from "class-transformer";
import { IsEmail, IsOptional, IsString } from "class-validator"


export class CreateUser {

    @IsString()
    @IsOptional()
    @Transform(({ value }) => value.toLowerCase())
    name: string;

    @IsEmail()
    @Transform(({ value }) => value.toLowerCase())
    email: string;

    @IsString()
    password: string;
}