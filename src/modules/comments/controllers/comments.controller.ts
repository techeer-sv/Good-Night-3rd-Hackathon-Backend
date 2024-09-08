import {
    Controller,
    Get,
    Post,
    Body,
    Param,
    Delete,
    Query,
} from '@nestjs/common';
import { CommentsService } from '../services/comments.service';
import { CreateCommentDto } from '../dto/create-comment.dto';
import { ApiTags } from '@nestjs/swagger';

@ApiTags('comments')
@Controller('comments')
export class CommentsController {
    constructor(private readonly commentsService: CommentsService) {}

    // 댓글 등록
    @Post()
    async create(@Body() createCommentDto: CreateCommentDto) {
        return await this.commentsService.create(createCommentDto);
    }

    // 댓글 목록 조회
    @Get(':id')
    findAll(
        @Param('id') id: string,
        @Query('limit') limit: string = '10',
        @Query('offset') offset: string = '0',
    ) {
        return this.commentsService.findAll(+id, +limit, +offset);
    }

    // 댓글 삭제
    @Delete(':id')
    remove(@Param('id') id: string) {
        return this.commentsService.remove(+id);
    }
}
