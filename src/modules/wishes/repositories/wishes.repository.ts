import { Injectable } from '@nestjs/common';
import { DataSource, Repository } from 'typeorm';
import { Wish } from '../domain/wish.entity';
import { CreateWishDto } from '../dto/create-wish.dto';

@Injectable()
export class WishesRepository extends Repository<Wish> {
    constructor(private readonly dataSource: DataSource) {
        super(Wish, dataSource.createEntityManager());
    }

    // 소원 등록
    createWish(createWishDto: CreateWishDto) {
        const wish = this.create({ ...createWishDto, isConfirm: '보류됨' });
        return this.save(wish);
    }

    // 소원 목록 조회
    findAll() {
        return this.createQueryBuilder('wish')
            .orderBy('wish.createdAt', 'DESC')
            .getMany();
    }

    // 소원 단일 조회
    findById(id: number) {
        return this.findOne({ where: { id: id } });
    }

    // 소원 삭제
    deleteWish(id: number) {
        return this.softDelete({ id: id });
    }
}
