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

    // 댓글 등록
    createComment(content: string, wish: Wish) {
        const comment = this.create({ content: content, wish: wish });
        return this.save(comment);
    }

    // 댓글 목록 조회
    async findAll(id: number, limit: number, offset: number) {
        return await this.createQueryBuilder('comment')
            .where('comment.wish_id = :id', { id: id })
            .limit(limit)
            .offset(offset)
            .getMany();
    }

    // 댓글 삭제
    deleteComment(id: number) {
        return this.softDelete({ id: id });
    }
}
