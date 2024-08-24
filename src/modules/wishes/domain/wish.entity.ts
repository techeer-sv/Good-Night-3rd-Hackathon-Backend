import {
    Column,
    Entity,
    PrimaryGeneratedColumn,
    CreateDateColumn,
    UpdateDateColumn,
    DeleteDateColumn,
} from 'typeorm';

@Entity('wish')
export class WishEntity {
    @PrimaryGeneratedColumn()
    id: number;

    @Column({ type: 'varchar', length: 50, comment: '제목' })
    title: string;

    @Column({ type: 'text', comment: '내용' })
    content: string;

    @Column({ type: 'varchar', length: 30, comment: '카테고리' })
    category: string;

    @Column({ type: 'varchar', length: 10, comment: '제목' })
    approved: string;

    @CreateDateColumn({ name: 'create_at', comment: '생성일' })
    createdAt: Date;

    @UpdateDateColumn({ name: 'update_at', comment: '수정일' })
    updatedAt?: Date;

    @DeleteDateColumn({ name: 'delete_at', comment: '삭제일' })
    deletedAt: boolean;
}
