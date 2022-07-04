package com.project.springbatch._56_reader_jpaPaging;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@RequiredArgsConstructor
@Configuration
public class JpaPagingConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(10)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Customer> customItemReader() {
        /*
           JpaPagingItemReader - doOpen() 호출 -> entityManager 생성
           AbstractItemCountingItemStreamItemReader - update() : 상태정보 업데이트
           JpaPagingItemReader - doReadPage()
           - 쿼리 수행 (pageSize 만큼 데이터를 가져온다.)
         */
        return new JpaPagingItemReaderBuilder<Customer>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(10)
                .queryString("select c from Customer c join fetch c.address")
                .build();
    }

    @Bean
    public ItemWriter<Customer> customItemWriter() {
        return items -> {
            for (Customer customer : items) {
                System.out.println(customer.getAddress().getLocation());
            }
        };
    }
}
