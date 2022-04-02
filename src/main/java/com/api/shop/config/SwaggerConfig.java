package com.api.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * http://localhost:8080/swagger-ui/
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {
        "com.api.shop.modules.controller"
})
public class SwaggerConfig {

    /** swagger */
    @Bean
    public Docket ShopApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Shop API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.shop.modules.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.ShopApiInfo())
                .tags(   new Tag("AuthController", "Auth API")
                       , new Tag("MemberController", "Member API")
                );

    }

    private ApiInfo ShopApiInfo() {
        return new ApiInfoBuilder()
                .title("shop API")
                .description("shop API")
                .termsOfServiceUrl("http://www.shop-api.com")
                .version("1.0")
                .build();
    }
}

