import {
  Entity,
  Column,
  PrimaryGeneratedColumn,
  ManyToOne,
  CreateDateColumn,
} from 'typeorm';
import { Wish } from '../../wishes/entities/wish.entity';

@Entity('comments')
export class Comment {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ nullable: false })
  content: string;

  @ManyToOne(() => Wish, (wish) => wish.comments)
  wish: Wish;

  @CreateDateColumn()
  createdAt: Date;

  @Column({ nullable: true })
  deleted_at?: Date;
}
