package com.oasis.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    private static final String API_TITLE = "Oasis Project";
    private static final String API_VERSION = "1.0.0.0";
    private static final String API_DESCRIPTION = "Oasis Project API Specification";

    @Bean
    public Docket api() {// Swagger 설정을 저장하는 docket been

        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(true) // Swagger 에서 제공해주는 기본 응답 코드 (200, 401, 403, 404) 등의 노출 여부
                .apiInfo(apiInfo()) // Swagger UI 로 노출할 정보
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oasis")) // api 스펙이 작성되어 있는 패키지 (Controller) 지정
                .paths(PathSelectors.any()) // apis 에 위치하는 API 중 특정 path 를 선택
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .build();
    }
}
