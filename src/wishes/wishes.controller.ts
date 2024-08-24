import {
  Controller,
  Post,
  Body,
  Get,
  Param,
  Patch,
  Query,
  Delete,
} from '@nestjs/common';
import { WishesService } from './wishes.service';
import { CreateWishDto } from './dtos/create-wish.dto';
import { UpdateWishStatusDto } from './dtos/update-wish-status.dto';

@Controller('wishes')
export class WishesController {
  constructor(private readonly wishesService: WishesService) {}

  @Post()
  createWish(@Body() createWishDto: CreateWishDto) {
    return this.wishesService.createWish(createWishDto);
  }

  @Get()
  getWishes(@Query('status') status: string, @Query('page') page: number) {
    return this.wishesService.getWishes(status, page);
  }

  @Get(':id')
  getWishById(@Param('id') id: number) {
    return this.wishesService.getWishById(id);
  }

  @Patch(':id/status')
  updateWishStatus(
    @Param('id') id: number,
    @Body() updateWishStatusDto: UpdateWishStatusDto,
  ) {
    return this.wishesService.updateWishStatus(id, updateWishStatusDto);
  }

  @Delete(':id')
  deleteWish(@Param('id') id: number) {
    return this.wishesService.deleteWish(id);
  }
}
