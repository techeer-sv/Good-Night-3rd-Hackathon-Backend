export class CreateWishDto {
  title: string;
  content: string;
  category:
    | 'career'
    | 'health'
    | 'relationships'
    | 'money'
    | 'goals'
    | 'studies'
    | 'other';
}
