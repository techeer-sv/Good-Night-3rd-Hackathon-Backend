import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  DeleteDateColumn,
} from 'typeorm';

@Entity()
export class Wish {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  title: string;

  @Column()
  content: string;

  @Column({
    type: 'varchar', // SQLite에서 문자열로 저장
    length: 50, // 적절한 길이 지정
  })
  category: string; // 문자열로 변경

  @CreateDateColumn()
  createdAt: Date;

  @Column({
    type: 'varchar', // SQLite에서 문자열로 저장
    length: 50, // 적절한 길이 지정
    default: 'PENDING', // 문자열로 기본값 설정
  })
  status: string; // 문자열로 변경

  @DeleteDateColumn()
  deletedAt?: Date;
}
