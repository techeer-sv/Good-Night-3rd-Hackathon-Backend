import { Injectable } from '@nestjs/common';
import { DataSource, Repository } from 'typeorm';
import { Wish } from '../domain/wish.entity';

@Injectable()
export class WishesConfirmRepository extends Repository<Wish> {
    constructor(private readonly dataSource: DataSource) {
        super(Wish, dataSource.createEntityManager());
    }

    // 보류됨 소원 목록 조회
    confirmList() {
        return this.find({ where: [{ isConfirm: '보류됨' }] });
    }

    // 소원 승인/거절
    confirmWish(id: number, isConfirm: string) {
        return this.update({ id: id }, { isConfirm: isConfirm });
    }
}
