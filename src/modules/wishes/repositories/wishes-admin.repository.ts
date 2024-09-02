import { Injectable } from '@nestjs/common';
import { DataSource, Repository } from 'typeorm';
import { Wish, WishStatus } from '../domain/wish.entity';

@Injectable()
export class WishesAdminRepository extends Repository<Wish> {
    constructor(private readonly dataSource: DataSource) {
        super(Wish, dataSource.createEntityManager());
    }

    // 소원 승인/거절
    confirmWish(id: number, isConfirm: string) {
        return this.update({ id: id }, { isConfirm: WishStatus[isConfirm] });
    }
}
