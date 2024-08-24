import {
    Controller,
    Get,
    Post,
    Body,
    Param,
    Delete,
    UsePipes,
    ValidationPipe,
    Patch,
} from '@nestjs/common';
import { WishesService } from '../services/wishes.service';
import { CreateWishDto } from '../dto/create-wish.dto';
import { UpdateWishDto } from '../dto/update-wish.dto';
import { ApiTags } from '@nestjs/swagger';

@ApiTags('wishes')
@Controller('wishes')
export class WishesController {
    constructor(private readonly wishesService: WishesService) {}

    @Post()
    @UsePipes(ValidationPipe)
    create(@Body() createWishDto: CreateWishDto) {
        return this.wishesService.create(createWishDto);
    }

    @Get()
    findAll() {
        return this.wishesService.findAll();
    }

    @Get(':id')
    findOne(@Param('id') id: string) {
        return this.wishesService.findOne(+id);
    }

    @Delete(':id')
    remove(@Param('id') id: string) {
        return this.wishesService.remove(+id);
    }

    @Patch()
    @UsePipes(ValidationPipe)
    confirm(@Body() updateWishDto: UpdateWishDto) {
        return this.wishesService.confirm(updateWishDto);
    }
}
