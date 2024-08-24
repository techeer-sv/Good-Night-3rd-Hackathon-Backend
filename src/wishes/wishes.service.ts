import { Injectable, NotFoundException } from '@nestjs/common';
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

  async findOne(id: number): Promise<Wish> {
    const wish = await this.wishRepository.findOne({
      where: { id, deleted_at: null, is_confirm: '승인됨' },
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
