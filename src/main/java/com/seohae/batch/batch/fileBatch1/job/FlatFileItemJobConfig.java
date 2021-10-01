package com.seohae.batch.batch.fileBatch1.job;

import com.seohae.batch.batch.fileBatch1.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * --job.name=csvFileItemReaderJob
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class FlatFileItemJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    /**
     * Job
     * @return
     */
    @Bean
    public Job flatFileItemJob() {
        return jobBuilderFactory.get("flatFileItemJob")
                .incrementer(new RunIdIncrementer())
                .start(flatFileItemStep())
                .build();
    }

    /**
     * Step
     * @return
     */
    @Bean
    public Step flatFileItemStep() {
        return stepBuilderFactory.get("flatFileItemStep")
                .<Customer, Customer>chunk(10)
                .reader(flatFileItemReaderStep())
                .writer(flatFileItemWriterStep())
                .build();
    }

    /**
     * 파일을 읽어들여 Customer.class 로 변환한다.
     * @return
     */
    @Bean
    @StepScope
    public FlatFileItemReader<Customer> flatFileItemReaderStep() {
        Resource inputFile = new ClassPathResource("input/customerFixedWidth.txt");

        return new FlatFileItemReaderBuilder<Customer>()
                .name("FlatFileItemReaderStep")
                .resource(inputFile)
                .fixedLength()
                /* Range 객체 (파싱해야할 칼럼의 시작 위치와 종료 위치를 나타낸다) 의 배열 지정 */
                .columns(new Range[]{new Range(1, 11), new Range(12, 12), new Range(13, 22),
                                    new Range(23, 26), new Range(27, 46), new Range(47, 62),
                                    new Range(63, 64), new Range(65, 69)})
                .names("firstName", "middleInitial", "lastName",
                        "addressNumber", "street", "city", "state", "zipCode")
                .targetType(Customer.class)
                .build();
    }

    /**
     * ItemWriter.write 메서드에 전달된 List 내 각 아이템에 대해 출력한다.
     * @return
     */
    @Bean
    public ItemWriter<Customer> flatFileItemWriterStep() {
        return (items) -> items.forEach(System.out::println);
    }


}
