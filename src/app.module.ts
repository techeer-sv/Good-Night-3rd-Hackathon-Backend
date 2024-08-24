import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ConfigModule } from '@nestjs/config';
import { WishesModule } from './wishes/wishes.module';
import { CommentsModule } from './comments/comments.module';
import { Wish } from './wishes/entities/wish.entity';
import { Comment } from './comments/entities/comment.entity';

@Module({
  imports: [
    ConfigModule.forRoot({
      isGlobal: true,
    }),
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: process.env.DB_HOST,
      port: parseInt(process.env.DB_PORT, 10),
      username: process.env.DB_USERNAME,
      password: process.env.DB_PASSWORD,
      database: process.env.DB_DATABASE,
      entities: [Wish,Comment],
      synchronize: true,
      logging: true,
    }),
    WishesModule,
    CommentsModule,
  ],
})
export class AppModule {}
