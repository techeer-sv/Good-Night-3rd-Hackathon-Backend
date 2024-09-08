import { Wish } from 'src/modules/wishes/domain/wish.entity';
import {
    Column,
    Entity,
    PrimaryGeneratedColumn,
    CreateDateColumn,
    UpdateDateColumn,
    DeleteDateColumn,
    ManyToOne,
    JoinColumn,
} from 'typeorm';

@Entity('comment')
export class Comment {
    @PrimaryGeneratedColumn()
    id: number;

    @Column({ type: 'text', comment: '내용' })
    content: string;

    @CreateDateColumn({ name: 'create_at', comment: '등록일' })
    createdAt: Date;

    @UpdateDateColumn({ name: 'update_at', comment: '수정일' })
    updatedAt?: Date;

    @DeleteDateColumn({ name: 'delete_at', comment: '삭제일' })
    deletedAt: boolean;

    @ManyToOne(() => Wish, (wish) => wish.comments)
    @JoinColumn({ name: 'wish_id' })
    wish: Wish;
}
