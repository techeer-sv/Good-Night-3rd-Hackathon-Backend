import { ConfigService } from '@nestjs/config';
import { TypeOrmModuleOptions } from '@nestjs/typeorm';

export const getDatabaseConfig = (
  configService: ConfigService,
): TypeOrmModuleOptions => ({
  type: 'postgres',
  host: configService.get<string>('app.database.host'),
  port: configService.get<number>('app.database.port'),
  username: configService.get<string>('app.database.username'),
  password: configService.get<string>('app.database.password'),
  database: configService.get<string>('app.database.name'),
  autoLoadEntities: true,
  synchronize: true,
});
