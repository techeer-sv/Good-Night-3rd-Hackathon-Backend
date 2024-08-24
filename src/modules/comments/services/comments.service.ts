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

    async create(createCommentDto: CreateCommentDto) {
        const { wishId } = createCommentDto;
        const wish = this.wishesRepository.findById(wishId);
        return this.commentsRepository.createComment(
            createCommentDto,
            await wish,
        );
    }

    findAll(id: number) {
        return this.commentsRepository.findAll(id);
    }

    remove(id: number) {
        return this.commentsRepository.deleteComment(id);
    }
}
