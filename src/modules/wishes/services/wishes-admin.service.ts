import { Injectable } from '@nestjs/common';
import { WishesAdminRepository } from '../repositories/wishes-admin.repository';
import { ConfirmWishDto } from '../dto/confirm-wish.dto';

@Injectable()
export class WishesAdminService {
    constructor(private wishesAdminRepository: WishesAdminRepository) {}

    // 소원 승인/거절
    confirm(confirmWishDto: ConfirmWishDto) {
        const { id, isConfirm } = confirmWishDto;
        return this.wishesAdminRepository.confirmWish(id, isConfirm);
    }
}
