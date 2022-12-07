package com.spring.batch._49_reader_flatfileFixedLength;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class FlatFilesFixedLengthConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flatFilesFixedLengthJob() {
        return jobBuilderFactory.get("flatFilesFixedLengthJob")
                .start(flatFilesFixedLengthStep1())
                .next(flatFilesFixedLengthStep2())
                .build();
    }

    @Bean
    public Step flatFilesFixedLengthStep1() {
        return stepBuilderFactory.get("flatFilesFixedLengthStep1")
                .<String, String>chunk(3)
                .reader(flatFilesFixedLengthItemReader())
                .writer(new ItemWriter() {
                    @Override
                    public void write(List items) throws Exception {
                        items.forEach(item -> System.out.println(item));
                    }
                })
                .build();
    }

    public FlatFileItemReader flatFilesFixedLengthItemReader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("flatFile")
                .resource(new FileSystemResource("C:\\lecture\\src\\main\\resources\\customer.txt"))
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(Customer.class)
                .linesToSkip(1)
                /** fixedLength 선언 */
                // FlatFileItemReaderBuilder.java - FixedLengthBuilder
                .fixedLength()
                .addColumns(new Range(1,5))
                .addColumns(new Range(6,9))
                .addColumns(new Range(10,11))
                // max 를 설정하지 않으면 끝까지 읽는다.
                // 1 ~ 끝, 6 ~ 끝, 10 ~ 끝
                /*.addColumns(new Range(1))
                .addColumns(new Range(6))
                .addColumns(new Range(10))*/
                .names("name","year","age")
                .build();
    }

    @Bean
    public Step flatFilesFixedLengthStep2() {
        return stepBuilderFactory.get("flatFilesFixedLengthStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
