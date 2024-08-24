import {
  Controller,
  Get,
  Post,
  Param,
  Body,
  Query,
  Delete,
  Patch,
} from '@nestjs/common';
import { WishesService } from './wishes.service';
import { CreateWishDto } from './dtos/create-wish.dto';

@Controller('wishes')
export class WishesController {
  constructor(private readonly wishesService: WishesService) {}

  @Post()
  create(@Body() createWishDto: CreateWishDto) {
    return this.wishesService.create(createWishDto);
  }

  @Get()
  findAll(
    @Query('isConfirmed') isConfirmed?: string,
    @Query('keyword') keyword?: string,
    @Query('category') category?: string,
    @Query('page') page: number = 1,
    @Query('limit') limit: number = 10,
  ) {
    return this.wishesService.findAll(
      isConfirmed,
      keyword,
      category,
      page,
      limit,
    );
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.wishesService.findOne(+id);
  }

  @Delete(':id')
  delete(@Param('id') id: string) {
    return this.wishesService.delete(+id);
  }

  @Patch(':id/approve')
  approve(@Param('id') id: string) {
    return this.wishesService.approveWish(+id);
  }

  @Patch(':id/reject')
  reject(@Param('id') id: string) {
    return this.wishesService.rejectWish(+id);
  }
}
