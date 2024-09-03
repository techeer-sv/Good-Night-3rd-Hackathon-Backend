import {
  Controller,
  Get,
  Post,
  Param,
  Body,
  Delete,
  Query,
} from '@nestjs/common';
import { CommentsService } from './comments.service';
import { CreateCommentDto } from './dtos/create-comment.dto';

@Controller('comments')
export class CommentsController {
  constructor(private readonly commentsService: CommentsService) {}

  @Post()
  create(@Body() createCommentDto: CreateCommentDto) {
    return this.commentsService.create(createCommentDto);
  }

  @Get(':wishId')
  findAll(
    @Param('wishId') wishId: number,
    @Query('page') page: number = 1,
    @Query('limit') limit: number = 10,
  ) {
    return this.commentsService.findAll(wishId, { page, limit });
  }

  @Delete(':id')
  delete(@Param('id') id: string) {
    return this.commentsService.delete(+id);
  }
}
