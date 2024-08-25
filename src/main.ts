import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // 기본 CORS 설정
  app.enableCors();

  await app.listen(8080);
}
bootstrap();
