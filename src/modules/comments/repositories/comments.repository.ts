import { Injectable } from '@nestjs/common';
import { DataSource, Repository } from 'typeorm';
import { Comment } from '../domain/comment.entity';

@Injectable()
export class CommentsRepository extends Repository<Comment> {
    constructor(private readonly dataSource: DataSource) {
        super(Comment, dataSource.createEntityManager());
    }
}
