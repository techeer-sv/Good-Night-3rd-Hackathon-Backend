import { IsNotEmpty } from 'class-validator';

export class CreateCommentDto {
    @IsNotEmpty()
    wishId: number;

    @IsNotEmpty()
    content: string;
}
