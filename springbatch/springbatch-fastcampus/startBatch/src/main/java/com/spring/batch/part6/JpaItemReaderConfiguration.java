package com.spring.batch.part6;

import com.spring.batch.part4.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.stream.Collectors;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JpaItemReaderConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory; /* JPA */

    @Bean
    public Job jpaItemReaderJob() throws Exception {
        return this.jobBuilderFactory.get("jpaItemReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(this.jpaStep()) /* 추가 */
                .build();
    }


    @Bean
    public Step jpaStep() throws Exception {
        return stepBuilderFactory.get("jpaStep")
                .<PersonEntity, PersonEntity>chunk(10)
                .reader(this.jpaCursorItemReader())
                .writer(itemWriter())
                .build();
    }

    private JpaCursorItemReader<PersonEntity> jpaCursorItemReader() throws Exception {
        JpaCursorItemReader<PersonEntity> jpaCursorItemReader
                    = new JpaCursorItemReaderBuilder<PersonEntity>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select p from Person p")
                .build();

        jpaCursorItemReader.afterPropertiesSet();

        return jpaCursorItemReader;
    }

    private ItemWriter<PersonEntity> itemWriter() {
        /* 일괄 처리 */
        return items -> log.info(items.stream()
                .map(PersonEntity::getName)
                .collect(Collectors.joining(", ")));
    }
}
