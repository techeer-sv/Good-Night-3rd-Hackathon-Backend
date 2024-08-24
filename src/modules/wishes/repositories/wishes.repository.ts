import { Injectable } from '@nestjs/common';
import { DataSource, Repository } from 'typeorm';
import { WishEntity } from '../domain/wish.entity';
import { CreateWishDto } from '../dto/create-wish.dto';

@Injectable()
export class WishesRepository extends Repository<WishEntity> {
    constructor(private readonly dataSource: DataSource) {
        super(WishEntity, dataSource.createEntityManager());
    }

    createWish(createWishDto: CreateWishDto) {
        const newWish = this.create({ ...createWishDto, isConfirm: '보류됨' });
        return this.save(newWish);
    }
}
