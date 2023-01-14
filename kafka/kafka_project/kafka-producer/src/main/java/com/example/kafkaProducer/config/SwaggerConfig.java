package com.example.kafkaProducer.config;

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
        "com.example.kafkaProducer.controller"
})
public class SwaggerConfig {

    /** swagger */
    @Bean
    public Docket TestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Kafka Producer API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.kafkaProducer.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.TestApiInfo())
                .tags(  new Tag("KafkaController", "Kafka Producer API")
                    );

    }

    private ApiInfo TestApiInfo() {
        return new ApiInfoBuilder()
                .title("Kafka Producer API")
                .description("Kafka Producer API")
                .termsOfServiceUrl("http://www.userservice-api.com")
                .version("1.0")
                .build();
    }
}