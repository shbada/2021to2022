package com.spring.batch._54_reader_jpaCursor;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@RequiredArgsConstructor
@Configuration
public class JpaCursorConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(2)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public JpaCursorItemReader<Customer> customItemReader() {

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("firstname", "A%");

        /*
          1) JpaCursorItemReader - doOpen()
          - createQuery()
          - 쿼리 수행 후, 결과를 query.getResultStream().iterator() 로 담는다.
          2) JpaCursorItemReader - update()
          3) JpaCursorItemReader - doRead()
          - open() 메서드에서 읽어온 모든 데이터를 하나씩 리턴한다.
          위 과정들이 chunk size 만큼 반복한다.


         */
        return new JpaCursorItemReaderBuilder()
                .name("jpaCursorItemReader")
                .queryString("select c from Customer c where firstname like :firstname") // jpql
                .entityManagerFactory(entityManagerFactory)
                .parameterValues(parameters)
//                .maxItemCount(10)
//                .currentItemCount(2)
                .build();
    }

    @Bean
    public ItemWriter<Customer> customItemWriter() {
        return items -> {
            for (Customer item : items) {
                System.out.println(item.toString());
            }
        };
    }
}
