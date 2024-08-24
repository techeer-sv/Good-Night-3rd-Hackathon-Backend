import { IsEnum, IsNotEmpty } from 'class-validator';
enum Category {
    진로 = '진로',
    건강 = '건강',
    인간관계 = '인간 관계',
    돈 = '돈',
    목표 = '목표',
    학업성적 = '학업/성적',
    기타 = '기타',
}

export class CreateWishDto {
    @IsNotEmpty()
    readonly title: string;

    @IsNotEmpty()
    readonly content: string;

    @IsNotEmpty()
    @IsEnum(Object.values(Category))
    readonly category: string;
}
