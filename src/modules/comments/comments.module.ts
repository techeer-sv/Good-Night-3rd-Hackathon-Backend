import { Module } from '@nestjs/common';
import { CommentsService } from './services/comments.service';
import { CommentsController } from './controllers/comments.controller';
import { TypeOrmModule } from '@nestjs/typeorm';

import { Comment } from './domain/comment.entity';
import { CommentsRepository } from './repositories/comments.repository';

@Module({
    imports: [TypeOrmModule.forFeature([Comment])],
    controllers: [CommentsController],
    providers: [CommentsService, CommentsRepository],
})
export class CommentsModule {}
