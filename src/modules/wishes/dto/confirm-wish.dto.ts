import { IsEnum, IsNotEmpty } from 'class-validator';
import { WishStatus } from '../domain/wish.entity';

export class ConfirmWishDto {
    @IsNotEmpty()
    readonly id: number;

    @IsNotEmpty()
    @IsEnum(WishStatus)
    readonly isConfirm: string;
}
