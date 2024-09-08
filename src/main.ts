import { NestFactory } from '@nestjs/core';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger';
import { AppModule } from './app.module';
import { ValidationPipe } from '@nestjs/common';

async function bootstrap() {
    const app = await NestFactory.create(AppModule);
    app.setGlobalPrefix('/api/v1');
    app.enableCors();
    app.useGlobalPipes(new ValidationPipe());

    const config = new DocumentBuilder()
        .setTitle('굿나잇 해커톤')
        .setDescription('API description')
        .setVersion('1.0')
        .addTag('wishes')
        .addTag('comments')
        .addTag('admin')
        .build();
    const document = SwaggerModule.createDocument(app, config);
    SwaggerModule.setup('api/v1', app, document);

    await app.listen(8000);
}
bootstrap();
