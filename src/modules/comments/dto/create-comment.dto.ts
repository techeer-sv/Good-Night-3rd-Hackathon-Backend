import { IsNotEmpty } from 'class-validator';

export class CreateCommentDto {
    @IsNotEmpty()
    readonly wishId: number;

    @IsNotEmpty()
    readonly content: string;
}
