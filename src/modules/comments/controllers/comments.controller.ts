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

@Controller('comments')
export class CommentsController {
    constructor(private readonly commentsService: CommentsService) {}

    @Post()
    @UsePipes(ValidationPipe)
    async create(@Body() createCommentDto: CreateCommentDto) {
        console.log(createCommentDto);
        return await this.commentsService.create(createCommentDto);
    }

    @Get()
    findAll() {
        return this.commentsService.findAll();
    }

    @Get(':id')
    findOne(@Param('id') id: string) {
        return this.commentsService.findOne(+id);
    }

    @Delete(':id')
    remove(@Param('id') id: string) {
        return this.commentsService.remove(+id);
    }
}
