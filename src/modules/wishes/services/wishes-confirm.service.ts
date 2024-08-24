import { Injectable } from '@nestjs/common';
import { WishesConfirmRepository } from '../repositories/wishes-confirm.repository';
import { ConfirmWishDto } from '../dto/confirm-wish.dto';

@Injectable()
export class WishesConfirmService {
    constructor(private wishesConfirmRepository: WishesConfirmRepository) {}

    // 보류됨 소원 목록 조회
    async confirmList(limit: number, offset: number) {
        return await this.wishesConfirmRepository.confirmList(limit, offset);
    }

    // 소원 승인/거절
    confirm(confirmWishDto: ConfirmWishDto) {
        const { id, isConfirm } = confirmWishDto;
        return this.wishesConfirmRepository.confirmWish(id, isConfirm);
    }
}
