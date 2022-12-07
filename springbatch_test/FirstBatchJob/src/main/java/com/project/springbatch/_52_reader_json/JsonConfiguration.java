package com.project.springbatch._52_reader_json;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/*
--job.name=jsonBatchJob
 */
@RequiredArgsConstructor
@Configuration
public class JsonConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jsonBatchJob() {
        return jobBuilderFactory.get("jsonBatchJob")
                .incrementer(new RunIdIncrementer())
                .start(jsonBatchStep1())
                .build();
    }

    @Bean
    public Step jsonBatchStep1() {
        return stepBuilderFactory.get("jsonBatchStep1")
                .<Customer, Customer>chunk(3)
                .reader(customJsonItemReader())
                .writer(customJsonItemWriter())
                .build();
    }

    @Bean
    public JsonItemReader<Customer> customJsonItemReader(){
        return new JsonItemReaderBuilder<Customer>()
                .name("customJsonItemReader")
                // JsonItemReader > doRead() > JacksonJsonObjectReader > read() 호출
                .resource(new ClassPathResource("item52/customer.json"))
                .jsonObjectReader(new JacksonJsonObjectReader<>(Customer.class))
                .build();
    }

    @Bean
    public ItemWriter<Customer> customJsonItemWriter() {
        return items -> {
            for (Customer item : items) {
                System.out.println(item.toString());
            }
        };
    }
}
