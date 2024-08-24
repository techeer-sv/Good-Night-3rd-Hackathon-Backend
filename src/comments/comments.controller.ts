import { Controller, Get, Post, Param, Body, Delete } from '@nestjs/common';
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
  findAll(@Param('wishId') wishId: string) {
    return this.commentsService.findAll(+wishId);
  }

  @Delete(':id')
  delete(@Param('id') id: string) {
    return this.commentsService.delete(+id);
  }
}
