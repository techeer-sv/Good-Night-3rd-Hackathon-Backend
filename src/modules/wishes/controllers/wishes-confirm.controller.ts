import {
    Controller,
    Body,
    UsePipes,
    ValidationPipe,
    Patch,
} from '@nestjs/common';
import { WishesConfirmService } from '../services/wishes-confirm.service';
import { UpdateWishDto } from '../dto/update-wish.dto';
import { ApiTags } from '@nestjs/swagger';

@ApiTags('confirm')
@Controller('confirm')
export class WishesConfirmController {
    constructor(private readonly wishesConfirmService: WishesConfirmService) {}

    @Patch()
    @UsePipes(ValidationPipe)
    confirm(@Body() updateWishDto: UpdateWishDto) {
        return this.wishesConfirmService.confirm(updateWishDto);
    }
}
