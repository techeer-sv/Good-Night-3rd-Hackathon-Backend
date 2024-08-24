import { Injectable } from '@nestjs/common';
import { CreateWishDto } from '../dto/create-wish.dto';
import { WishesRepository } from '../repositories/wishes.repository';

@Injectable()
export class WishesService {
    constructor(private wishesRepository: WishesRepository) {}

    create(createWishDto: CreateWishDto) {
        return this.wishesRepository.createWish(createWishDto);
    }

    findAll() {
        return this.wishesRepository.findAll();
    }

    findOne(id: number) {
        return this.wishesRepository.findById(id);
    }

    remove(id: number) {
        return this.wishesRepository.deleteWish(id);
    }
}
