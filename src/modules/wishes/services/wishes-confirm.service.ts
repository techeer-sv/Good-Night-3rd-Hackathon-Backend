import { Injectable } from '@nestjs/common';
import { WishesConfirmRepository } from '../repositories/wishes-confirm.repository';
import { UpdateWishDto } from '../dto/update-wish.dto';

@Injectable()
export class WishesConfirmService {
    constructor(private wishesConfirmRepository: WishesConfirmRepository) {}

    // 보류됨 소원 목록 조회
    confirmList() {
        return this.wishesConfirmRepository.confirmList();
    }

    // 소원 승인/거절
    confirm(updateWishDto: UpdateWishDto) {
        const { id, isConfirm } = updateWishDto;
        return this.wishesConfirmRepository.confirmWish(id, isConfirm);
    }
}
