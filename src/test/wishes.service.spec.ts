import { Test, TestingModule } from '@nestjs/testing';
import { Repository } from 'typeorm';
import { getRepositoryToken } from '@nestjs/typeorm';
import { WishesService } from '../wishes/wishes.service';
import { Wish } from '../wishes/entities/wish.entity';
import { BadRequestException, NotFoundException } from '@nestjs/common';

describe('WishesService', () => {
  let service: WishesService;
  let repository: Repository<Wish>;

  // WishesService 에서 사용하는 Wish 리포지토리를 모킹
  const mockWishRepository = {
    find: jest.fn(),
    findOne: jest.fn(),
    save: jest.fn(),
    createQueryBuilder: jest.fn().mockReturnThis(),
    andWhere: jest.fn().mockReturnThis(),
    orderBy: jest.fn().mockReturnThis(),
    skip: jest.fn().mockReturnThis(),
    take: jest.fn().mockReturnThis(),
    getMany: jest.fn(),
    getOne: jest.fn(),
    update: jest.fn(),
  };
  // 테스트가 실행되기 전에 호출
  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        WishesService,
        {
          provide: getRepositoryToken(Wish),
          useValue: mockWishRepository,
        },
      ],
    }).compile();

    service = module.get<WishesService>(WishesService);
    repository = module.get<Repository<Wish>>(getRepositoryToken(Wish));
  });

  it('define 테스트', () => {
    expect(service).toBeDefined();
  });
  // findAll 메서드 테스트
  describe('findAll', () => {
    it('소원 배열 반환 테스트', async () => {
      const result = [
        {
          id: 1,
          title: 'Wish 1',
          content: 'Content 1',
          is_confirm: '승인됨',
          createdAt: new Date(),
          deleted_at: null,
        },
        {
          id: 2,
          title: 'Wish 2',
          content: 'Content 2',
          is_confirm: '보류됨',
          createdAt: new Date(),
          deleted_at: null,
        },
      ];

      mockWishRepository.getMany.mockResolvedValue(result);

      expect(await service.findAll()).toEqual(result);
    });

    it('승인 여부에 따른 필터링 테스트', async () => {
      const result = [
        {
          id: 1,
          title: 'Wish 1',
          content: 'Content 1',
          is_confirm: '승인됨',
          createdAt: new Date(),
          deleted_at: null,
        },
      ];

      mockWishRepository.andWhere.mockReturnThis();
      mockWishRepository.getMany.mockResolvedValue(result);

      expect(await service.findAll({ isConfirmed: 'true' })).toEqual(result);
      expect(mockWishRepository.andWhere).toHaveBeenCalledWith(
        'wish.is_confirm = :isConfirmed',
        { isConfirmed: '승인됨' },
      );
    });

    it('페이지네이션 테스트', async () => {
      const result = [
        {
          id: 1,
          title: 'Wish 1',
          content: 'Content 1',
          is_confirm: '승인됨',
          createdAt: new Date(),
          deleted_at: null,
        },
      ];

      mockWishRepository.getMany.mockResolvedValue(result);

      expect(await service.findAll({ page: 2, limit: 5 })).toEqual(result);
      expect(mockWishRepository.skip).toHaveBeenCalledWith(5);
      expect(mockWishRepository.take).toHaveBeenCalledWith(5);
    });

    it('키워드 검색 테스트', async () => {
      const result = [
        {
          id: 1,
          title: 'Hackathon Wish',
          content: 'Content about hackathon',
          is_confirm: '승인됨',
          createdAt: new Date(),
          deleted_at: null,
        },
      ];

      mockWishRepository.andWhere.mockReturnThis();
      mockWishRepository.getMany.mockResolvedValue(result);

      expect(await service.findAll({ keyword: 'hackathon' })).toEqual(result);
      expect(mockWishRepository.andWhere).toHaveBeenCalledWith(
        '(wish.title LIKE :keyword OR wish.content LIKE :keyword)',
        { keyword: '%hackathon%' },
      );
    });

    it('예상치 못한 승인 여부 요청시 예외 처리 테스트', async () => {
      await expect(service.findAll({ isConfirmed: 'invalid' })).rejects.toThrow(
        BadRequestException,
      );
    });
  });
  // findOne 메서드 테스트
  describe('findOne', () => {
    it('단일 소원 반환 테스트', async () => {
      const id = 1;
      const result = {
        id,
        title: 'Wish 1',
        content: 'Content 1',
        is_confirm: '승인됨',
        createdAt: new Date(),
        deleted_at: null,
      };

      jest.spyOn(mockWishRepository, 'findOne').mockResolvedValue(result);

      expect(await service.findOne(id)).toEqual(result);
    });

    it('소원을 찾을 수 없을 때 예외 처리 테스트', async () => {
      const id = 1;
      jest.spyOn(mockWishRepository, 'findOne').mockResolvedValue(null);

      await expect(service.findOne(id)).rejects.toThrow(NotFoundException);
    });
  });

  describe('delete', () => {
    it('소원 논리 삭제 테스트', async () => {
      const id = 1;
      const updateSpy = jest
        .spyOn(repository, 'update')
        .mockResolvedValue(undefined);

      await service.delete(id);

      expect(updateSpy).toHaveBeenCalledWith(id, {
        deleted_at: expect.any(Date),
      });
    });
  });

  describe('approveWish', () => {
    it('승인 여부 승인 테스트', async () => {
      const id = 1;
      const wish = { id, is_confirm: '보류됨', deleted_at: null } as Wish;
      const saveSpy = jest
        .spyOn(repository, 'save')
        .mockResolvedValue({ ...wish, is_confirm: '승인됨' });
      jest.spyOn(repository, 'findOne').mockResolvedValue(wish);

      const result = await service.approveWish(id);

      expect(saveSpy).toHaveBeenCalledWith({ ...wish, is_confirm: '승인됨' });
      expect(result.is_confirm).toBe('승인됨');
    });
  });

  describe('rejectWish', () => {
    it('승인 여부 거절 테스트', async () => {
      const id = 1;
      const wish = { id, is_confirm: '보류됨', deleted_at: null } as Wish;
      const saveSpy = jest
        .spyOn(repository, 'save')
        .mockResolvedValue({ ...wish, is_confirm: '거절됨' });
      jest.spyOn(repository, 'findOne').mockResolvedValue(wish);

      const result = await service.rejectWish(id);

      expect(saveSpy).toHaveBeenCalledWith({ ...wish, is_confirm: '거절됨' });
      expect(result.is_confirm).toBe('거절됨');
    });
  });

  describe('updateConfirmStatus', () => {
    it('소원을 찾을 수 없을 때 예외 처리 테스트', async () => {
      const id = 1;
      const status = '승인됨';
      jest.spyOn(repository, 'findOne').mockResolvedValue(null);

      await expect(service['updateConfirmStatus'](id, status)).rejects.toThrow(
        NotFoundException,
      );
    });

    it('승인 상태 업데이트 테스트', async () => {
      const id = 1;
      const status = '승인됨';
      const wish = { id, is_confirm: '보류됨', deleted_at: null } as Wish;
      const saveSpy = jest
        .spyOn(repository, 'save')
        .mockResolvedValue({ ...wish, is_confirm: status });
      jest.spyOn(repository, 'findOne').mockResolvedValue(wish);

      const result = await service['updateConfirmStatus'](id, status);

      expect(saveSpy).toHaveBeenCalledWith({ ...wish, is_confirm: status });
      expect(result.is_confirm).toBe(status);
    });
  });
});
