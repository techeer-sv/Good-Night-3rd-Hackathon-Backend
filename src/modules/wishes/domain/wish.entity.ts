import { Comment } from 'src/modules/comments/domain/comment.entity';
import {
    Column,
    Entity,
    PrimaryGeneratedColumn,
    CreateDateColumn,
    UpdateDateColumn,
    DeleteDateColumn,
    OneToMany,
} from 'typeorm';

export enum WishStatus {
    APPROVED = 'APPROVED', // 소원이 승인된 상태
    PENDING = 'PENDING', // 소원이 아직 검토 중인 상태
    REJECTED = 'REJECTED', // 소원이 거절된 상태
}

export enum Category {
    CAREER = 'CAREER', // 진로
    HEALTH = 'HEALTH', // 건강
    RELATIONSHIPS = 'RELATIONSHIPS', // 인간관계
    MONEY = 'MONEY', // 돈
    GOALS = 'GOALS', // 목표
    ACADEMICS = 'ACADEMICS', // 학업/성적
    OTHER = 'OTHER', // 기타
}

@Entity('wish')
export class Wish {
    @PrimaryGeneratedColumn()
    id: number;

    @Column({ type: 'varchar', length: 50, comment: '제목' })
    title: string;

    @Column({ type: 'text', comment: '내용' })
    content: string;

    @Column({ type: 'varchar', length: 30, comment: '카테고리' })
    category: Category;

    @Column({
        type: 'varchar',
        length: 10,
        name: 'is_confirm',
        comment: '승인 상태',
        default: WishStatus.PENDING,
    })
    isConfirm: WishStatus;

    @CreateDateColumn({ name: 'create_at', comment: '등록일' })
    createdAt: Date;

    @UpdateDateColumn({ name: 'update_at', comment: '수정일' })
    updatedAt?: Date;

    @DeleteDateColumn({ name: 'delete_at', comment: '삭제일' })
    deletedAt: boolean;

    @OneToMany(() => Comment, (comment) => comment.wish)
    comments: Comment[];
}
