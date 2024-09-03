import { Wish } from '../wishes/entities/wish.entity';

export const dummyWish = (overrides: Partial<Wish> = {}): Wish => {
  return {
    id: 1,
    title: 'Default Title',
    content: 'Default Content',
    category: 'Default Category',
    createdAt: new Date(),
    is_confirm: '보류됨',
    deleted_at: null,
    comments: [],
    ...overrides, // 전달된 필드만 오버라이드
  };
};
