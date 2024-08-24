import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Comment } from './entities/comment.entity';
import { CreateCommentDto } from './dtos/create-comment.dto';
import { Wish } from '../wishes/entities/wish.entity';

@Injectable()
export class CommentsService {
  constructor(
    @InjectRepository(Comment)
    private readonly commentRepository: Repository<Comment>,
    @InjectRepository(Wish)
    private readonly wishRepository: Repository<Wish>,
  ) {}
  // 댓글 생성
  async create(createCommentDto: CreateCommentDto): Promise<Comment> {
    const wish = await this.wishRepository.findOne({
      where: { id: createCommentDto.wishId },
    });
    if (!wish) {
      throw new NotFoundException(
        `Wish with ID ${createCommentDto.wishId} not found`,
      );
    }
    const comment = this.commentRepository.create({
      content: createCommentDto.content,
      wish,
    });
    return this.commentRepository.save(comment);
  }

  // 소원의 모든 댓글 조회
  async findAll(
    wishId: number,
    page: number = 1,
    limit: number = 10,
  ): Promise<Comment[]> {
    const query = this.commentRepository.createQueryBuilder('comment');

    query
      .where('comment.wishId = :wishId', { wishId })
      .andWhere('comment.deleted_at IS NULL')
      .orderBy('comment.createdAt', 'DESC');

    // 페이지네이션
    const offset = (page - 1) * limit;
    query.skip(offset).take(limit);

    return query.getMany();
  }

  // 댓글 논리 삭제
  async delete(id: number): Promise<void> {
    await this.commentRepository.update(id, { deleted_at: new Date() });
  }
}
