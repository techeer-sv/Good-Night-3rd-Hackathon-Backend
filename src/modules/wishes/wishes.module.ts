import { Module } from '@nestjs/common';
import { WishesService } from './services/wishes.service';
import { WishesController } from './controllers/wishes.controller';
import { WishesRepository } from './repositories/wishes.repository';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Wish } from './domain/wish.entity';
import { WishesAdminController } from './controllers/wishes-admin.controller';
import { WishesAdminService } from './services/wishes-admin.service';
import { WishesAdminRepository } from './repositories/wishes-admin.repository';

@Module({
    imports: [TypeOrmModule.forFeature([Wish])],
    controllers: [WishesController, WishesAdminController],
    providers: [
        WishesService,
        WishesAdminService,
        WishesRepository,
        WishesAdminRepository,
    ],
})
export class WishesModule {}
