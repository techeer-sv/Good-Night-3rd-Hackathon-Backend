import { IsNotEmpty, IsEnum } from 'class-validator';

export enum WishCategory {
  CAREER = '진로',
  HEALTH = '건강',
  RELATIONSHIPS = '인간 관계',
  MONEY = '돈',
  GOALS = '목표',
  STUDY = '학업/성적',
  OTHER = '기타',
}

export enum WishStatus {
  APPROVED = '승인됨',
  PENDING = '보류됨',
  REJECTED = '거절됨',
}

export class CreateWishDto {
  @IsNotEmpty()
  title: string;

  @IsNotEmpty()
  content: string;

  @IsEnum(WishCategory)
  category: WishCategory;
}
