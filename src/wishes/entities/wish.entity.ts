import {
  Entity,
  Column,
  PrimaryGeneratedColumn,
  OneToMany,
  CreateDateColumn,
} from 'typeorm';
import { Comment } from '../../comments/entities/comment.entity';

@Entity('wishes')
export class Wish {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ nullable: false })
  title: string;

  @Column({ nullable: false })
  content: string;

  @Column({ nullable: false })
  category: string;

  @CreateDateColumn()
  createdAt: Date;

  @Column({
    type: 'enum',
    enum: ['승인됨', '보류됨', '거절됨'],
    default: '보류됨',
  })
  is_confirm: string;

  @Column({ type: 'timestamp', nullable: true })
  deleted_at: Date | null;

  @OneToMany(() => Comment, (comment) => comment.wish)
  comments: Comment[];
}
