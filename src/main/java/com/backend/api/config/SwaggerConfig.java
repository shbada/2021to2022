package com.backend.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Backend API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.backend.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.docketInfo());
    }

    private ApiInfo docketInfo() {
        return new ApiInfoBuilder()
                .title("Backend API")
                .description("Backend Restful API")
                .version("1.0")
                .build();
    }
}
