import {
    Controller,
    Get,
    Post,
    Body,
    Param,
    Delete,
    UsePipes,
    ValidationPipe,
} from '@nestjs/common';
import { WishesService } from '../services/wishes.service';
import { CreateWishDto } from '../dto/create-wish.dto';
import { UpdateWishDto } from '../dto/update-wish.dto';
import { ApiTags } from '@nestjs/swagger';

@ApiTags('wishes')
@Controller('wishes')
export class WishesController {
    constructor(private readonly wishesService: WishesService) {}

    // 소원 등록
    @Post()
    @UsePipes(ValidationPipe)
    create(@Body() createWishDto: CreateWishDto) {
        return this.wishesService.create(createWishDto);
    }

    // 소원 목록 조회
    @Get()
    findAll() {
        return this.wishesService.findAll();
    }

    // 소원 단일 조회
    @Get(':id')
    findOne(@Param('id') id: string) {
        return this.wishesService.findOne(+id);
    }

    // 소원 삭제
    @Delete(':id')
    remove(@Param('id') id: string) {
        return this.wishesService.remove(+id);
    }
}
