import { Injectable } from '@nestjs/common';
import { WishesConfirmRepository } from '../repositories/wishes-confirm.repository';
import { UpdateWishDto } from '../dto/update-wish.dto';

@Injectable()
export class WishesConfirmService {
    constructor(private wishesConfirmRepository: WishesConfirmRepository) {}

    // 소원 승인/거절
    confirm(updateWishDto: UpdateWishDto) {
        const { id, isConfirm } = updateWishDto;
        return this.wishesConfirmRepository.confirmWish(id, isConfirm);
    }
}
