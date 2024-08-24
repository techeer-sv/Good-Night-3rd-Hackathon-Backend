import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Wish } from './wishes.entity';
import { CreateWishDto, WishStatus } from './dtos/create-wish.dto';
import { UpdateWishStatusDto } from './dtos/update-wish-status.dto';

@Injectable()
export class WishesService {
  constructor(
    @InjectRepository(Wish)
    private readonly wishRepository: Repository<Wish>,
  ) {}

  async createWish(createWishDto: CreateWishDto): Promise<Wish> {
    const wish = this.wishRepository.create(createWishDto);
    return this.wishRepository.save(wish);
  }

  async getWishes(status: string, page: number): Promise<Wish[]> {
    const query = this.wishRepository.createQueryBuilder('wish');
    if (status) {
      query.andWhere('wish.status = :status', { status });
    }
    query.skip((page - 1) * 10).take(10);
    query.orderBy('wish.createdAt', 'DESC');
    return query.getMany();
  }

  async getWishById(id: number): Promise<Wish> {
    const wish = await this.wishRepository.findOne({
      where: { id, deletedAt: null, status: WishStatus.APPROVED },
    });
    if (!wish) {
      throw new NotFoundException(`Wish with id ${id} not found`);
    }
    return wish;
  }

  async updateWishStatus(
    id: number,
    updateWishStatusDto: UpdateWishStatusDto,
  ): Promise<Wish> {
    const wish = await this.wishRepository.findOne({ where: { id } });
    if (!wish) {
      throw new NotFoundException(`Wish with id ${id} not found`);
    }
    wish.status = updateWishStatusDto.status;
    return this.wishRepository.save(wish);
  }

  async deleteWish(id: number): Promise<void> {
    const wish = await this.wishRepository.findOne({ where: { id } });
    if (!wish) {
      throw new NotFoundException(`Wish with id ${id} not found`);
    }
    await this.wishRepository.softRemove(wish);
  }
}
