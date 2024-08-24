import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { CommentsController } from './comments.controller';
import { CommentsService } from './comments.service';
import { Comment } from './comments.entity';
import { Wish } from '../wishes/wishes.entity';

@Module({
  imports: [
    TypeOrmModule.forFeature([Comment, Wish]), // Comment와 Wish 엔티티를 TypeORM에 등록
  ],
  controllers: [CommentsController], // CommentsController를 모듈의 컨트롤러로 등록
  providers: [CommentsService], // CommentsService를 모듈의 프로바이더로 등록
})
export class CommentsModule {}
