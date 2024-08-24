import { IsNotEmpty } from 'class-validator';

export class CreateWishDto {
    @IsNotEmpty()
    readonly title: string;

    @IsNotEmpty()
    readonly content: string;

    @IsNotEmpty()
    readonly category: string;
}
