package com.spring.batch._50_reader_flatExceptionHandling;

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
import org.springframework.core.io.ClassPathResource;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ExceptionHandlingConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flatExceptionHandlingJob() {
        return jobBuilderFactory.get("flatExceptionHandlingJob")
                .start(flatExceptionHandlingStep1())
                .next(flatExceptionHandlingStep2())
                .build();
    }

    @Bean
    public Step flatExceptionHandlingStep1() {
        return stepBuilderFactory.get("flatExceptionHandlingStep1")
                .<String, String>chunk(3)
                .reader(itemReader())
                .writer(new ItemWriter() {
                    @Override
                    public void write(List items) throws Exception {
                        items.forEach(item -> System.out.println(item));
                    }
                })
                .build();
    }

    public FlatFileItemReader itemReader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("flatFile")
                .resource(new ClassPathResource("customerException.txt"))
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(Customer.class)
                .linesToSkip(1)
                .fixedLength()
                /* 예외를 잡지 않겠다 */
                .strict(false) // 예외가 발생하지 않고 오류가 있던 row 는 pass 된다.
                .addColumns(new Range(1,5))
                .addColumns(new Range(6,9))
                .addColumns(new Range(10,11))
                .names("name","year","age")
                .build();
    }

    @Bean
    public Step flatExceptionHandlingStep2() {
        return stepBuilderFactory.get("flatExceptionHandlingStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
