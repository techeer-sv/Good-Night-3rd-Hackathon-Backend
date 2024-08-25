import {
    Controller,
    Body,
    UsePipes,
    ValidationPipe,
    Patch,
    Get,
    Query,
} from '@nestjs/common';
import { WishesConfirmService } from '../services/wishes-confirm.service';
import { ConfirmWishDto } from '../dto/confirm-wish.dto';
import { ApiTags } from '@nestjs/swagger';

@ApiTags('admin')
@Controller('admin')
export class WishesConfirmController {
    constructor(private readonly wishesConfirmService: WishesConfirmService) {}

    // 보류됨 소원 목록 조회
    @Get('wishes')
    async confirmList(
        @Query('limit') limit: string = '10',
        @Query('offset') offset: string = '0',
    ) {
        return await this.wishesConfirmService.confirmList(+limit, +offset);
    }

    // 소원 승인/거절
    @Patch('wishes')
    @UsePipes(ValidationPipe)
    confirm(@Body() confirmWishDto: ConfirmWishDto) {
        return this.wishesConfirmService.confirm(confirmWishDto);
    }
}
