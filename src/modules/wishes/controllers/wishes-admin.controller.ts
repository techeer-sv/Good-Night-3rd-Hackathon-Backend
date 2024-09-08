import { Controller, Body, Patch, Get, Query } from '@nestjs/common';
import { WishesAdminService } from '../services/wishes-admin.service';
import { ConfirmWishDto } from '../dto/confirm-wish.dto';
import { ApiTags, ApiQuery } from '@nestjs/swagger';

@ApiTags('admin')
@Controller('admin')
export class WishesAdminController {
    constructor(private readonly wishesAdminService: WishesAdminService) {}

    // 소원 승인/거절
    @Patch('wishes')
    confirm(@Body() confirmWishDto: ConfirmWishDto) {
        return this.wishesAdminService.confirm(confirmWishDto);
    }
}
