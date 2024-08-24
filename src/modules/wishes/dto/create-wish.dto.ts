import { IsNotEmpty } from 'class-validator';

export class CreateWishDto {
    @IsNotEmpty()
    title: string;

    @IsNotEmpty()
    content: string;

    @IsNotEmpty()
    category: string;
}
