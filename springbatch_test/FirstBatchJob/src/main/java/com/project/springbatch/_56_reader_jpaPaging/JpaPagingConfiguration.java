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
    public Job jpaPagingJob() throws Exception {
        return jobBuilderFactory.get("jpaPagingJob")
                .incrementer(new RunIdIncrementer())
                .start(jpaPagingStep1())
                .build();
    }

    @Bean
    public Step jpaPagingStep1() throws Exception {
        return stepBuilderFactory.get("jpaPagingStep1")
                .<PagingCustomer, PagingCustomer>chunk(10)
                .reader(jpaPagingCustomItemReader())
                .writer(jpaPagingCustomItemWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<PagingCustomer> jpaPagingCustomItemReader() {
        /*
           JpaPagingItemReader - doOpen() 호출 -> entityManager 생성
           AbstractItemCountingItemStreamItemReader - update() : 상태정보 업데이트
           JpaPagingItemReader - doReadPage()
           - 쿼리 수행 (pageSize 만큼 데이터를 가져온다.)
         */
        return new JpaPagingItemReaderBuilder<PagingCustomer>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(10)
                .queryString("select c from Customer c join fetch c.address")
                .build();
    }

    @Bean
    public ItemWriter<PagingCustomer> jpaPagingCustomItemWriter() {
        return items -> {
            for (PagingCustomer pagingCustomer : items) {
                System.out.println(pagingCustomer.getAddress().getLocation());
            }
        };
    }
}
