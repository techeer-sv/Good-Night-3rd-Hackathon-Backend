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
import { CommentsService } from '../services/comments.service';
import { CreateCommentDto } from '../dto/create-comment.dto';
import { ApiTags } from '@nestjs/swagger';

@ApiTags('comments')
@Controller('comments')
export class CommentsController {
    constructor(private readonly commentsService: CommentsService) {}

    @Post()
    @UsePipes(ValidationPipe)
    async create(@Body() createCommentDto: CreateCommentDto) {
        return await this.commentsService.create(createCommentDto);
    }

    @Get(':id')
    findAll(@Param('id') id: string) {
        return this.commentsService.findAll(+id);
    }

    @Delete(':id')
    remove(@Param('id') id: string) {
        return this.commentsService.remove(+id);
    }
}