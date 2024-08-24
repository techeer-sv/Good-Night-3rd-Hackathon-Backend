import { Injectable } from '@nestjs/common';
import { CreateWishDto } from '../dto/create-wish.dto';
import { WishesRepository } from '../repositories/wishes.repository';
import { UpdateWishDto } from '../dto/update-wish.dto';

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

    confirm(updateWishDto: UpdateWishDto) {
        const { id, isConfirm } = updateWishDto;
        return this.wishesRepository.confirmWish(id, isConfirm);
    }
}
