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

  async create(createWishDto: CreateWishDto): Promise<Wish> {
    const wish = this.wishRepository.create(createWishDto);
    return this.wishRepository.save(wish);
  }

  async findAll(
    isConfirmed?: string,
    page: number = 1,
    limit: number = 10,
  ): Promise<Wish[]> {
    const query = this.wishRepository.createQueryBuilder('wish');

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

    query.andWhere('wish.deleted_at IS NULL').orderBy('wish.createdAt', 'DESC');

    // Apply pagination
    const offset = (page - 1) * limit;
    query.skip(offset).take(limit);

    return query.getMany();
  }

  async findOne(id: number): Promise<Wish> {
    const wish = await this.wishRepository.findOne({
      where: { id, is_confirm: '승인됨', deleted_at: null },
      cache: false, // 캐시 비활성화
    });
    if (!wish) {
      throw new NotFoundException(`Wish with ID ${id} not found`);
    }
    return wish;
  }

  async delete(id: number): Promise<void> {
    await this.wishRepository.update(id, { deleted_at: new Date() });
  }

  async approveWish(id: number): Promise<Wish> {
    return this.updateConfirmStatus(id, '승인됨');
  }

  async rejectWish(id: number): Promise<Wish> {
    return this.updateConfirmStatus(id, '거절됨');
  }

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
