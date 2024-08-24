import { IsEnum, IsNotEmpty } from 'class-validator';
enum Confirm {
    승인됨 = '승인됨',
    보류됨 = '보류됨',
    거절됨 = '거절됨',
}

export class ConfirmWishDto {
    @IsNotEmpty()
    readonly id: number;

    @IsNotEmpty()
    @IsEnum(Object.values(Confirm))
    readonly isConfirm: string;
}
