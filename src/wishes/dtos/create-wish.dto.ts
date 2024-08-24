export class CreateWishDto {
  title: string;
  content: string;
  category:
    | '진로'
    | '건강'
    | '인간 관계'
    | '돈'
    | '목표'
    | '학업/성적'
    | '기타';
}
