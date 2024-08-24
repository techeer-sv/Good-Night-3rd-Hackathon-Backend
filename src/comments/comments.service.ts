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

  async findAll(wishId: number): Promise<Comment[]> {
    return this.commentRepository.find({
      where: { wish: { id: wishId }, deleted_at: null },
    });
  }
}
