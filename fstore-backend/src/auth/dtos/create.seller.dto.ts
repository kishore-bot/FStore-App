import { Transform } from "class-transformer"
import { IsEmail, IsOptional, IsString, IsStrongPassword } from "class-validator"

export class CreateSeller {
    
    @IsString()
    @Transform(({ value }) => value.toLowerCase())
    @IsOptional()
    name:string

    @IsEmail()
    @Transform(({ value }) => value.toLowerCase())
    email:string

    @IsStrongPassword()
    password:string
}