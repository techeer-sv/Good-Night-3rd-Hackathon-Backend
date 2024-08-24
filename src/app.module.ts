import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { WishesModule } from './wishes/wishes.module'; // WishModule의 경로 확인
import { CommentsModule } from './comments/comments.module'; // CommentModule의 경로 확인
import { Wish } from './wishes/wishes.entity'; // Wish 엔티티의 경로 확인
import { Comment } from './comments/comments.entity'; // Comment 엔티티의 경로 확인

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'sqlite', // 사용할 데이터베이스의 유형 (sqlite, mysql, postgres 등)
      database: 'database.sqlite', // 데이터베이스 파일 또는 연결 정보
      entities: [Wish, Comment], // 사용할 엔티티들
      synchronize: true, // 자동으로 데이터베이스 스키마를 동기화할지 여부 (개발 중에만 true로 설정)
    }),
    WishesModule,
    CommentsModule,
  ],
  controllers: [], // 전역 컨트롤러를 추가할 경우 여기에 정의
  providers: [], // 전역 프로바이더를 추가할 경우 여기에 정의
})
export class AppModule {}
