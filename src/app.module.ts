import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { WishesModule } from './modules/wishes/wishes.module';
import { ConfigModule } from '@nestjs/config';
import { DataSource } from 'typeorm';
import { Wish } from './modules/wishes/domain/wish.entity';
import { Comment } from './modules/comments/domain/comment.entity';
import { CommentsModule } from './modules/comments/comments.module';

@Module({
    imports: [
        ConfigModule.forRoot({ isGlobal: true, envFilePath: '.env' }),
        TypeOrmModule.forRoot({
            type: 'mysql',
            host: process.env.DB_HOST,
            port: +process.env.DB_PORT,
            username: process.env.DB_USERNAME,
            password: process.env.DB_PASSWORD,
            database: process.env.DB_NAME,
            entities: [Wish, Comment],
            synchronize: true,
        }),
        WishesModule,
        CommentsModule,
    ],
    controllers: [AppController],
    providers: [AppService],
})
export class AppModule {
    constructor(private dataSource: DataSource) {}
}
