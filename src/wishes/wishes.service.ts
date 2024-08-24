import {
  BadRequestException,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Wish } from './entities/wish.entity';
import { CreateWishDto } from './dtos/create-wish.dto';

@Injectable()
export class WishesService {
  constructor(
    @InjectRepository(Wish)
    private readonly wishRepository: Repository<Wish>,
  ) {}

  // 소원 생성
  async create(createWishDto: CreateWishDto): Promise<Wish> {
    const wish = this.wishRepository.create(createWishDto);
    return this.wishRepository.save(wish);
  }

  // 모든 소원 조회
  async findAll(
    isConfirmed?: string,
    keyword?: string,
    category?: string,
    page: number = 1,
    limit: number = 10,
  ): Promise<Wish[]> {
    const query = this.wishRepository.createQueryBuilder('wish');

    // 쿼리파라미터 값에 따라 필터링
    if (isConfirmed === 'true') {
      query.andWhere('wish.is_confirm = :isConfirmed', {
        isConfirmed: '승인됨',
      });
    } else if (isConfirmed === 'false') {
      query.andWhere('wish.is_confirm = :isConfirmed', {
        isConfirmed: '거절됨',
      });
    } else if (isConfirmed) {
      throw new BadRequestException('올바르지 않은 값입니다.');
    }

    // 키워드 검색
    if (keyword) {
      query.andWhere(
        '(wish.title LIKE :keyword OR wish.content LIKE :keyword)',
        { keyword: `%${keyword}%` },
      );
    }

    // 카테고리 검색
    if (category) {
      query.andWhere('wish.category = :category', { category });
    }

    query.andWhere('wish.deleted_at IS NULL').orderBy('wish.createdAt', 'DESC');

    // 페이지네이션
    const offset = (page - 1) * limit;
    query.skip(offset).take(limit);

    return query.getMany();
  }

  // 단일 소원 조회
  async findOne(id: number): Promise<Wish> {
    const wish = await this.wishRepository.findOne({
      where: { id, is_confirm: '승인됨', deleted_at: null }, // 승인 상태이며 삭제 처리되지 않은 소원만 찾아옴
      cache: false, // 캐시 비활성화
    });
    if (!wish) {
      throw new NotFoundException(`Wish with ID ${id} not found`);
    }
    return wish;
  }

  // 소원 논리 삭제
  async delete(id: number): Promise<void> {
    await this.wishRepository.update(id, { deleted_at: new Date() });
  }

  // 소원 승인
  async approveWish(id: number): Promise<Wish> {
    return this.updateConfirmStatus(id, '승인됨');
  }

  // 소원 거절
  async rejectWish(id: number): Promise<Wish> {
    return this.updateConfirmStatus(id, '거절됨');
  }

  // 승인 상태 업데이트
  private async updateConfirmStatus(id: number, status: string): Promise<Wish> {
    const wish = await this.wishRepository.findOne({
      where: { id, deleted_at: null },
    });
    if (!wish) {
      throw new NotFoundException(`Wish with ID ${id} not found`);
    }
    wish.is_confirm = status;
    return this.wishRepository.save(wish);
  }
}
