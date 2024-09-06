package com.example.TecheerTreeBackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  // JPA Auditing 기능 활성화
public class Config {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .version("v1.0") //버전
                .title("API") //이름
                .description("API"); //설명
        return new OpenAPI()
                .info(info);
    }

}