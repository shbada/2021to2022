package com.test.technology.configs;

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

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages={
        "com.test.technology.controllers"
})
public class SwaggerConfig {

    /** swagger */
    @Bean
    public Docket AdminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Test API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.technology.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.TestApiInfo())
                .tags(new Tag("RedisController", "Redis API")
                        , new Tag("DeptController", "JPA+Oracle DB 연동 API")
                        , new Tag("TestController", "TEST API")
                    );

    }

    private ApiInfo TestApiInfo() {
        return new ApiInfoBuilder()
                .title("Test API")
                .description("technology API")
                .termsOfServiceUrl("http://www.seohae-technology.com")
                .version("1.0")
                .build();
    }
}