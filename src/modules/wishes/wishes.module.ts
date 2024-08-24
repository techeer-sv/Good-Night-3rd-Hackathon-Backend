import { Module } from '@nestjs/common';
import { WishesService } from './services/wishes.service';
import { WishesController } from './controllers/wishes.controller';
import { WishesRepository } from './repositories/wishes.repository';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Wish } from './domain/wish.entity';
import { WishesConfirmController } from './controllers/wishes-confirm.controller';
import { WishesConfirmService } from './services/wishes-confirm.service';
import { WishesConfirmRepository } from './repositories/wishes-confirm.repository';

@Module({
    imports: [TypeOrmModule.forFeature([Wish])],
    controllers: [WishesController, WishesConfirmController],
    providers: [
        WishesService,
        WishesConfirmService,
        WishesRepository,
        WishesConfirmRepository,
    ],
})
export class WishesModule {}
