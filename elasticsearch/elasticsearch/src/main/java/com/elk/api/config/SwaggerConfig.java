package com.elk.api.config;

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

@Configuration
@EnableSwagger2
@ComponentScan(basePackages={
        "com.elk.api.controllers"
})
public class SwaggerConfig {

    /** swagger */
    @Bean
    public Docket AdminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Sample API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.elk.api.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.TestApiInfo())
                .tags(  new Tag("DataController", "Data API")
                      , new Tag("ElkController", "Elasticsearch API")
                      , new Tag("SearchController", "SearchController API")
                    );

    }

    private ApiInfo TestApiInfo() {
        return new ApiInfoBuilder()
                .title("Elk API")
                .description("Elk API")
                .termsOfServiceUrl("http://www.elk-api.com")
                .version("1.0")
                .build();
    }
}