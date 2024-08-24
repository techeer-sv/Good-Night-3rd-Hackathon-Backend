import { Injectable } from '@nestjs/common';
import { DataSource, Repository } from 'typeorm';
import { Comment } from '../domain/comment.entity';
import { CreateCommentDto } from '../dto/create-comment.dto';
import { Wish } from 'src/modules/wishes/domain/wish.entity';

@Injectable()
export class CommentsRepository extends Repository<Comment> {
    constructor(private readonly dataSource: DataSource) {
        super(Comment, dataSource.createEntityManager());
    }

    createComment(createCommentDto: CreateCommentDto, wish: Wish) {
        const comment = this.create({ ...createCommentDto, wish: wish });
        return this.save(comment);
    }

    async findAll(id: number) {
        return await this.createQueryBuilder('comment')
            .where('comment.wish_id = :id', { id: id })
            .getMany();
    }

    deleteComment(id: number) {
        return this.softDelete({ id: id });
    }
}
