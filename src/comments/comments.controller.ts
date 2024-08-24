import {
  Controller,
  Post,
  Body,
  Get,
  Param,
  Query,
  Delete,
} from '@nestjs/common';
import { CommentsService } from './comments.service';
import { CreateCommentDto } from './dtos/create-comment.dto';

@Controller('comments')
export class CommentsController {
  constructor(private readonly commentsService: CommentsService) {}

  @Post()
  createComment(@Body() createCommentDto: CreateCommentDto) {
    return this.commentsService.createComment(createCommentDto);
  }

  @Get(':wishId')
  getComments(@Param('wishId') wishId: number, @Query('page') page: number) {
    return this.commentsService.getComments(wishId, page);
  }

  @Delete(':id')
  deleteComment(@Param('id') id: number) {
    return this.commentsService.deleteComment(id);
  }
}
