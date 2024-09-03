import { Test, TestingModule } from '@nestjs/testing';
import { Repository } from 'typeorm';
import { getRepositoryToken } from '@nestjs/typeorm';
import { CommentsService } from '../comments/comments.service';
import { Comment } from '../comments/entities/comment.entity';
import { Wish } from '../wishes/entities/wish.entity';
import { CreateCommentDto } from '../comments/dtos/create-comment.dto';
import { NotFoundException } from '@nestjs/common';
import { createDummyComments, dummyWish } from '../utils/fixtures';

describe('CommentsService', () => {
  let service: CommentsService;
  let commentRepository: Repository<Comment>;
  let wishRepository: Repository<Wish>;

  const mockCommentRepository = {
    find: jest.fn(),
    findOne: jest.fn(),
    save: jest.fn(),
    createQueryBuilder: jest.fn().mockReturnThis(),
    where: jest.fn().mockReturnThis(),
    andWhere: jest.fn().mockReturnThis(),
    orderBy: jest.fn().mockReturnThis(),
    skip: jest.fn().mockReturnThis(),
    take: jest.fn().mockReturnThis(),
    getMany: jest.fn(),
    update: jest.fn(),
    getOne: jest.fn(),
    create: jest.fn(),
  };

  const mockWishRepository = {
    findOne: jest.fn(),
  };

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        CommentsService,
        {
          provide: getRepositoryToken(Comment),
          useValue: mockCommentRepository,
        },
        {
          provide: getRepositoryToken(Wish),
          useValue: mockWishRepository,
        },
      ],
    }).compile();

    service = module.get<CommentsService>(CommentsService);
    commentRepository = module.get<Repository<Comment>>(
      getRepositoryToken(Comment),
    );
    wishRepository = module.get<Repository<Wish>>(getRepositoryToken(Wish));
  });

  it('define 테스트', () => {
    expect(service).toBeDefined();
  });

  describe('create', () => {
    it('댓글 작성 테스트', async () => {
      const createCommentDto: CreateCommentDto = {
        content: '첫번째 댓글',
        wishId: 1,
      };
      const wish = dummyWish({ id: 1 });
      const newComment = {
        id: 1,
        ...createCommentDto,
        wish,
        createdAt: new Date(),
        deleted_at: null,
      } as Comment;

      jest.spyOn(wishRepository, 'findOne').mockResolvedValue(wish);
      jest.spyOn(commentRepository, 'create').mockReturnValue(newComment);
      jest.spyOn(commentRepository, 'save').mockResolvedValue(newComment);

      expect(await service.create(createCommentDto)).toEqual(newComment);
    });

    it('소원을 찾을 수 없을 때 예외처리 테스트', async () => {
      const createCommentDto = {
        content: '첫번째 댓글',
        wishId: 1,
      };

      jest.spyOn(wishRepository, 'findOne').mockResolvedValue(null);

      await expect(service.create(createCommentDto)).rejects.toThrow(
        new NotFoundException(
          `Wish with ID ${createCommentDto.wishId} not found`,
        ),
      );
    });
  });

  describe('findAll', () => {
    it('소원의 전체 댓글 조회 테스트', async () => {
      const comments = [
        {
          id: 1,
          content: '첫번째 댓글',
          wish: { id: 1 } as Wish,
          deleted_at: null,
          createdAt: new Date(),
        },
        {
          id: 2,
          content: '두번째 댓글',
          wish: { id: 1 } as Wish,
          deleted_at: null,
          createdAt: new Date(),
        },
      ];

      jest
        .spyOn(commentRepository.createQueryBuilder(), 'getMany')
        .mockResolvedValue(comments);

      expect(await service.findAll(1)).toEqual(comments);
    });

    it('페이지네이션 테스트', async () => {
      const comments = createDummyComments(10);
      const queryBuilder = commentRepository.createQueryBuilder();
      jest.spyOn(queryBuilder, 'getMany').mockResolvedValue(comments);
      jest.spyOn(queryBuilder, 'skip').mockReturnThis();
      jest.spyOn(queryBuilder, 'take').mockReturnThis();

      await service.findAll(1, { page: 2, limit: 5 });

      expect(queryBuilder.skip).toHaveBeenCalledWith(5);
      expect(queryBuilder.take).toHaveBeenCalledWith(5);
    });
  });

  describe('delete', () => {
    it('댓글 논리 삭제 테스트', async () => {
      const id = 1;

      jest.spyOn(commentRepository, 'update').mockResolvedValue(undefined);

      await service.delete(id);

      expect(commentRepository.update).toHaveBeenCalledWith(id, {
        deleted_at: expect.any(Date),
      });
    });
  });
});
