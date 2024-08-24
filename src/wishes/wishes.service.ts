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

  async delete(id: number): Promise<void> {
    await this.wishRepository.update(id, { deleted_at: new Date() });
  }
}
