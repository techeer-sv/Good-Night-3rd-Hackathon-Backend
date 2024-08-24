import { Injectable } from '@nestjs/common';
import { CreateWishDto } from '../dto/create-wish.dto';
import { WishesRepository } from '../repositories/wishes.repository';

@Injectable()
export class WishesService {
    constructor(private wishesRepository: WishesRepository) {}

    // 소원 등록
    async create(createWishDto: CreateWishDto) {
        return await this.wishesRepository.createWish(createWishDto);
    }

    // 소원 목록 조회 - 승인/미승인
    async findAll(confirm: number) {
        return await this.wishesRepository.findAll(confirm);
    }

    // 소원 단일 조회
    findOne(id: number) {
        return this.wishesRepository.findById(id);
    }

    // 소원 삭제
    remove(id: number) {
        return this.wishesRepository.deleteWish(id);
    }
}
