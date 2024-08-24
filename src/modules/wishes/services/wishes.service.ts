import { Injectable } from '@nestjs/common';
import { CreateWishDto } from '../dto/create-wish.dto';
import { UpdateWishDto } from '../dto/update-wish.dto';
import { WishesRepository } from '../repositories/wishes.repository';

@Injectable()
export class WishesService {
    constructor(private wishesRepository: WishesRepository) {}

    create(createWishDto: CreateWishDto) {
        return this.wishesRepository.createWish(createWishDto);
    }

    findAll() {
        return `This action returns all wishes`;
    }

    findOne(id: number) {
        return `This action returns a #${id} wish`;
    }

    update(id: number, updateWishDto: UpdateWishDto) {
        return `This action updates a #${id} wish`;
    }

    remove(id: number) {
        return this.wishesRepository.deleteWish(id);
    }
}
