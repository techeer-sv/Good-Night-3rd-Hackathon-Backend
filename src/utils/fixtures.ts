import { Wish } from '../wishes/entities/wish.entity';
import { Comment } from '../comments/entities/comment.entity';

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

const createDummyComment = (id: number, wishId: number = 1): Comment => {
  return {
    id,
    content: `Comment ${id}`,
    wish: { id: wishId } as Wish,
    deleted_at: null,
    createdAt: new Date(),
  } as Comment;
};

export const createDummyComments = (
  count: number,
  wishId: number = 1,
): Comment[] => {
  return Array.from({ length: count }, (_, i) =>
    createDummyComment(i + 1, wishId),
  );
};
