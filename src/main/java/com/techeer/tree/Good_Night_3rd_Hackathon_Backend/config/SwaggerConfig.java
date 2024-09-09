package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .components(new Components())
        .info(apiInfo());
  }

  private Info apiInfo() {
    return new Info()
        .title("Techeer Tree") // API의 제목
        .description("Techeer Tree API는 사용자가 소원과 목표를 기록하고 공유할 수 있는 플랫폼을 제공합니다. 이 API를 통해 소원을 생성, 조회, 삭제하고, 승인 또는 거절할 수 있으며, 댓글 기능을 통해 소통할 수 있습니다.") // API에 대한 설명
        .version("1.0.0"); // API의 버전
  }
}