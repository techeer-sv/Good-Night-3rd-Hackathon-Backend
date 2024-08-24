import {
    Controller,
    Body,
    UsePipes,
    ValidationPipe,
    Patch,
    Get,
} from '@nestjs/common';
import { WishesConfirmService } from '../services/wishes-confirm.service';
import { ConfirmWishDto } from '../dto/confirm-wish.dto';
import { ApiTags } from '@nestjs/swagger';

@ApiTags('confirm')
@Controller('confirm')
export class WishesConfirmController {
    constructor(private readonly wishesConfirmService: WishesConfirmService) {}

    // 보류됨 소원 목록 조회
    @Get()
    confirmList() {
        return this.wishesConfirmService.confirmList();
    }

    // 소원 승인/거절
    @Patch()
    @UsePipes(ValidationPipe)
    confirm(@Body() confirmWishDto: ConfirmWishDto) {
        return this.wishesConfirmService.confirm(confirmWishDto);
    }
}
