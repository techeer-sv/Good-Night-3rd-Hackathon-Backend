import { Injectable } from '@nestjs/common';
import { DataSource, Repository } from 'typeorm';
import { WishEntity } from '../domain/wish.entity';
import { CreateWishDto } from '../dto/create-wish.dto';

@Injectable()
export class WishesRepository extends Repository<WishEntity> {
    constructor(private readonly dataSource: DataSource) {
        super(WishEntity, dataSource.createEntityManager());
    }

    createBoard(createWishDto: CreateWishDto) {
        const wish = this.create({ ...createWishDto });
        return this.save(wish);
    }
}
