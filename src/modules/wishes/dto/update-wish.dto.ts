import { IsNotEmpty } from 'class-validator';

export class UpdateWishDto {
    @IsNotEmpty()
    id: number;

    @IsNotEmpty()
    isConfirm: string;
}
