import { Injectable } from '@nestjs/common';
import { DataSource, Repository } from 'typeorm';
import { Wish } from '../domain/wish.entity';

@Injectable()
export class WishesConfirmRepository extends Repository<Wish> {
    constructor(private readonly dataSource: DataSource) {
        super(Wish, dataSource.createEntityManager());
    }

    // 소원 승인/거절
    confirmWish(id: number, isConfirm: string) {
        return this.update({ id: id }, { isConfirm: isConfirm });
    }
}
