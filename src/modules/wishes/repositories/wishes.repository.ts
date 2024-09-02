import { Injectable } from '@nestjs/common';
import { DataSource, Repository } from 'typeorm';
import { Category, Wish, WishStatus } from '../domain/wish.entity';
import { CreateWishDto } from '../dto/create-wish.dto';

@Injectable()
export class WishesRepository extends Repository<Wish> {
    constructor(private readonly dataSource: DataSource) {
        super(Wish, dataSource.createEntityManager());
    }

    // 소원 등록
    createWish(createWishDto: CreateWishDto) {
        const wish = this.create({
            ...createWishDto,
            category: Category[createWishDto.category],
        });
        return this.save(wish);
    }

    // 소원 목록 조회 - 승인/미승인
    async findAll(
        confirm: number,
        limit: number,
        offset: number,
    ): Promise<Wish[]> {
        const queryBuilder = this.createQueryBuilder('wish');

        if (confirm) {
            // 승인된 경우
            queryBuilder.where('wish.is_confirm = :isConfirm', {
                isConfirm: '승인됨',
            });
        } else {
            // 미승인된 경우
            queryBuilder.where('wish.is_confirm IN (:...isConfirm)', {
                isConfirm: ['보류됨', '거절됨'],
            });
        }

        return queryBuilder
            .orderBy('wish.createdAt', 'DESC')
            .limit(limit)
            .offset(offset)
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
