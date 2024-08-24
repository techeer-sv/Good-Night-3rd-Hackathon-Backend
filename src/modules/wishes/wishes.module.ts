import { Module } from '@nestjs/common';
import { WishesService } from './services/wishes.service';
import { WishesController } from './controllers/wishes.controller';
import { WishesRepository } from './repositories/wishes.repository';
import { TypeOrmModule } from '@nestjs/typeorm';
import { WishEntity } from './domain/wish.entity';

@Module({
    imports: [TypeOrmModule.forFeature([WishEntity])],
    controllers: [WishesController],
    providers: [WishesService, WishesRepository],
})
export class WishesModule {}
