import { IsEnum, IsNotEmpty } from 'class-validator';
import { Category } from '../domain/wish.entity';

export class CreateWishDto {
    @IsNotEmpty()
    readonly title: string;

    @IsNotEmpty()
    readonly content: string;

    @IsNotEmpty()
    @IsEnum(Category)
    readonly category: string;
}
