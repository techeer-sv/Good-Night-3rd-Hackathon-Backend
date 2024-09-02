import { Injectable } from '@nestjs/common';
import { WishesAdminRepository } from '../repositories/wishes-admin.repository';
import { ConfirmWishDto } from '../dto/confirm-wish.dto';

@Injectable()
export class WishesAdminService {
    constructor(private wishesAdminRepository: WishesAdminRepository) {}

    // 보류됨 소원 목록 조회
    async confirmList(limit: number, offset: number) {
        return await this.wishesAdminRepository.confirmList(limit, offset);
    }

    // 소원 승인/거절
    confirm(confirmWishDto: ConfirmWishDto) {
        const { id, isConfirm } = confirmWishDto;
        return this.wishesAdminRepository.confirmWish(id, isConfirm);
    }
}
