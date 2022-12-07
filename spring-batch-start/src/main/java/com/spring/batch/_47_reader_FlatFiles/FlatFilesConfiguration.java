package com.spring.batch._47_reader_FlatFiles;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@RequiredArgsConstructor
@Configuration
public class FlatFilesConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flatFilesJob() {
        return jobBuilderFactory.get("flatFilesJob")
                .start(flatFilesStep1())
                .next(flatFilesStep2())
                .build();
    }

    @Bean
    public Step flatFilesStep1() {
        return stepBuilderFactory.get("flatFilesStep1")
                .<String, String>chunk(5)
                .reader(itemReader())
                /* 1) 1 ~ 5
                   2) 6 ~ 10
                   데이터가 10개일때 write 는 2번 수행되겠다.
                 */
                .writer(items -> System.out.println("items = " + items))
                .build();
    }

    @Bean
    public Step flatFilesStep2() {
        return stepBuilderFactory.get("flatFilesStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public ItemReader itemReader(){

        FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("/customer.csv"));

        /* DefaultLineMapper */
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
        lineMapper.setFieldSetMapper(new CustomerFieldSetMapper()); /* filedSetMapper */

        itemReader.setLineMapper(lineMapper);
        itemReader.setLinesToSkip(1); // 첫번째 row 건너뜀

        return itemReader;
    }
}