import { Injectable } from '@nestjs/common';
import { DataSource, Repository, SelectQueryBuilder } from 'typeorm';
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
    async findWishes(
        status: string[],
        order: 'ASC' | 'DESC',
        limit: number,
        offset: number,
    ): Promise<Wish[]> {
        const queryBuilder = this.createQueryBuilder('wish');
        this.applyStatusFilter(queryBuilder, status);
        return queryBuilder
            .orderBy('wish.createdAt', order)
            .limit(limit)
            .offset(offset)
            .getMany();
    }

    // 상태 필터를 적용하는 메서드
    private applyStatusFilter(
        queryBuilder: SelectQueryBuilder<Wish>,
        status: string[],
    ): void {
        if (status.length > 0) {
            queryBuilder.where('wish.is_confirm IN (:...status)', { status });
        }
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
