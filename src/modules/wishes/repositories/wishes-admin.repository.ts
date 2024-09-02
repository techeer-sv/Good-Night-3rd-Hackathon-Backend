import { Injectable } from '@nestjs/common';
import { DataSource, Repository } from 'typeorm';
import { Wish } from '../domain/wish.entity';

@Injectable()
export class WishesAdminRepository extends Repository<Wish> {
    constructor(private readonly dataSource: DataSource) {
        super(Wish, dataSource.createEntityManager());
    }

    // 보류됨 소원 목록 조회
    async confirmList(limit: number, offset: number) {
        return await this.createQueryBuilder('wish')
            .where('wish.is_confirm = :isConfirm', { isConfirm: '보류됨' })
            .orderBy('wish.createdAt', 'ASC') // 오래된 순으로 정렬 (ASC)
            .limit(limit)
            .offset(offset)
            .getMany();
    }

    // 소원 승인/거절
    confirmWish(id: number, isConfirm: string) {
        return this.update({ id: id }, { isConfirm: isConfirm });
    }
}
