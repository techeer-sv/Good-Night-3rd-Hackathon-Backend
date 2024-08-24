import { Injectable } from '@nestjs/common';
import { CreateCommentDto } from '../dto/create-comment.dto';
import { CommentsRepository } from '../repositories/comments.repository';
import { WishesRepository } from 'src/modules/wishes/repositories/wishes.repository';

@Injectable()
export class CommentsService {
    constructor(
        private commentsRepository: CommentsRepository,
        private wishesRepository: WishesRepository,
    ) {}

    // 댓글 등록
    async create(createCommentDto: CreateCommentDto) {
        const { wishId, content } = createCommentDto;
        const wish = this.wishesRepository.findById(wishId);
        return this.commentsRepository.createComment(content, await wish);
    }

    // 댓글 목록 조회
    findAll(id: number) {
        return this.commentsRepository.findAll(id);
    }

    // 댓글 삭제
    remove(id: number) {
        return this.commentsRepository.deleteComment(id);
    }
}
