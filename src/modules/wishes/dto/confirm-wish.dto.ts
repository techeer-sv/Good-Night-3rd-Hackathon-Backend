import { IsNotEmpty } from 'class-validator';

export class ConfirmWishDto {
    @IsNotEmpty()
    readonly id: number;

    @IsNotEmpty()
    readonly isConfirm: string;
}
