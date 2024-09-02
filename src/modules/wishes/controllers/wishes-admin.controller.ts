import { Controller, Body, Patch, Get, Query } from '@nestjs/common';
import { WishesAdminService } from '../services/wishes-admin.service';
import { ConfirmWishDto } from '../dto/confirm-wish.dto';
import { ApiTags, ApiQuery } from '@nestjs/swagger';

@ApiTags('admin')
@Controller('admin')
export class WishesAdminController {
    constructor(private readonly wishesAdminService: WishesAdminService) {}

    // 보류됨 소원 목록 조회
    @Get('wishes')
    @ApiQuery({ name: 'limit', required: false })
    @ApiQuery({ name: 'offset', required: false })
    async confirmList(
        @Query('limit') limit: string = '10',
        @Query('offset') offset: string = '0',
    ) {
        return await this.wishesAdminService.confirmList(+limit, +offset);
    }

    // 소원 승인/거절
    @Patch('wishes')
    confirm(@Body() confirmWishDto: ConfirmWishDto) {
        return this.wishesAdminService.confirm(confirmWishDto);
    }
}
