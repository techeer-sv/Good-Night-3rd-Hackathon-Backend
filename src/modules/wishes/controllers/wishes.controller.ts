import {
    Controller,
    Get,
    Post,
    Body,
    Param,
    Delete,
    Query,
    BadRequestException,
} from '@nestjs/common';
import { WishesService } from '../services/wishes.service';
import { CreateWishDto } from '../dto/create-wish.dto';
import { ApiQuery, ApiTags } from '@nestjs/swagger';
import { WishStatus } from '../domain/wish.entity';

@ApiTags('wishes')
@Controller('wishes')
export class WishesController {
    constructor(private readonly wishesService: WishesService) {}

    // 소원 등록
    @Post()
    async create(@Body() createWishDto: CreateWishDto): Promise<any> {
        return await this.wishesService.create(createWishDto);
    }

    // 소원 목록 조회 - 승인/미승인
    @Get()
    @ApiQuery({ name: 'limit', required: false, type: Number })
    @ApiQuery({ name: 'offset', required: false, type: Number })
    async findWishes(
        @Query('status') status: string,
        @Query('order') order: string = 'DESC',
        @Query('limit') limit: string = '10',
        @Query('offset') offset: string = '0',
    ): Promise<any> {
        const statusArray = status.split(',');
        for (const s of statusArray) {
            if (!Object.values(WishStatus).includes(s as WishStatus)) {
                throw new BadRequestException(`Invalid status value: ${s}`);
            }
        }
        if (order !== 'ASC' && order !== 'DESC') {
            throw new BadRequestException(
                'Invalid order parameter. It should be ASC or DESC.',
            );
        }
        return await this.wishesService.findWishes(
            statusArray,
            order,
            +limit,
            +offset,
        );
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
