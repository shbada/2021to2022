package com.project.springbatch._48_reader_flatfileDelimete;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/*
--job.name=flatFilesDelimitedJob
 */
@RequiredArgsConstructor
@Configuration
public class FlatFilesDelimitedConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flatFilesDelimitedJob() {
        return jobBuilderFactory.get("flatFilesDelimitedJob")
                .start(flatFilesDelimitedStep1())
                .next(flatFilesDelimitedStep2())
                .build();
    }

    @Bean
    public Step flatFilesDelimitedStep1() {
        return stepBuilderFactory.get("flatFilesDelimitedStep1")
                .<String, String>chunk(5)
                .reader(itemDelimitedReader())
                /* 1) 1 ~ 5
                   2) 6 ~ 10
                   데이터가 10개일때 write 는 2번 수행되겠다.
                 */
                .writer(items -> System.out.println("items = " + items))
                .build();
    }

    @Bean
    public Step flatFilesDelimitedStep2() {
        return stepBuilderFactory.get("flatFilesDelimitedStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed 33");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public ItemReader itemDelimitedReader(){
        // defaultLineMapper 은 스프링배치가 제공해주는 파일이 있고, 이걸 default 로 사용한다.
        return new FlatFileItemReaderBuilder<Customer>()
                .name("flatFile")
                .resource(new ClassPathResource("/item47/customer.csv"))
                // fieldSetMapper 도 스프링 배치가 제공해주는 fieldSetMapper()-targetType() 로 대체할 수 있다.
//                .fieldSetMapper(new CustomerDelimitedFieldSetMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(Customer.class)
                .linesToSkip(1)
                // default (,)
                // DelimitedLineTokenizer 를 default 로 실행한다.
                .delimited().delimiter(",")
                .names("name", "age", "year")
                .build();
    }
}