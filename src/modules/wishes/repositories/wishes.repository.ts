import { Injectable } from '@nestjs/common';
import { DataSource, Repository } from 'typeorm';
import { Wish } from '../domain/wish.entity';
import { CreateWishDto } from '../dto/create-wish.dto';
import { UpdateWishDto } from '../dto/update-wish.dto';

@Injectable()
export class WishesRepository extends Repository<Wish> {
    constructor(private readonly dataSource: DataSource) {
        super(Wish, dataSource.createEntityManager());
    }

    createWish(createWishDto: CreateWishDto) {
        const wish = this.create({ ...createWishDto, isConfirm: '보류됨' });
        return this.save(wish);
    }

    findAll() {
        return this.createQueryBuilder('wish')
            .orderBy('wish.createdAt', 'DESC')
            .getMany();
    }

    findById(id: number) {
        return this.findOne({ where: { id: id } });
    }

    deleteWish(id: number) {
        return this.softDelete({ id: id });
    }

    confirmWish(id: number, isConfirm: string) {
        return this.update({ id: id }, { isConfirm: isConfirm });
    }
}