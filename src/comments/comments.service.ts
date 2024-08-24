import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Comment } from './comments.entity';
import { CreateCommentDto } from './dtos/create-comment.dto';

@Injectable()
export class CommentsService {
  constructor(
    @InjectRepository(Comment)
    private readonly commentRepository: Repository<Comment>,
  ) {}

  async createComment(createCommentDto: CreateCommentDto): Promise<Comment> {
    const comment = this.commentRepository.create(createCommentDto);
    comment.wish = { id: createCommentDto.wishId } as any; // wish 엔티티를 참조
    return this.commentRepository.save(comment);
  }

  async getComments(wishId: number, page: number): Promise<Comment[]> {
    const query = this.commentRepository.createQueryBuilder('comment');
    query.where('comment.wishId = :wishId', { wishId });
    query.skip((page - 1) * 10).take(10);
    query.orderBy('comment.createdAt', 'DESC');
    return query.getMany();
  }

  async deleteComment(id: number): Promise<void> {
    const comment = await this.commentRepository.findOne({ where: { id } });
    if (!comment) {
      throw new NotFoundException(`Comment with id ${id} not found`);
    }
    await this.commentRepository.softRemove(comment);
  }
}
