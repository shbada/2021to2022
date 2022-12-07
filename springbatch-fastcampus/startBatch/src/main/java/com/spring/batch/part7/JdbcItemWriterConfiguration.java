package com.spring.batch.part7;

import com.spring.batch.part4.CustomItemReader;
import com.spring.batch.part4.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JdbcItemWriterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job jdbcItemWriterJob() throws Exception {
        return this.jobBuilderFactory.get("jdbcItemWriterJob")
                .incrementer(new RunIdIncrementer())
                .start(this.jdbcItemWriterStep())
                .build();
    }

    @Bean
    public Step jdbcItemWriterStep() throws Exception {
        return this.stepBuilderFactory.get("jdbcItemWriterStep")
                .<Person, Person>chunk(10)
                .reader(this.itemReader())
                //.processor()
                .writer(this.jdbcBatchItemWriter())
                .build();
    }

    /**
     * jdbc Writer
     * @return
     */
    private ItemWriter<? super Person> jdbcBatchItemWriter() {
        JdbcBatchItemWriter<Person> itemWriter = new JdbcBatchItemWriterBuilder<Person>()
                .dataSource(dataSource)
                /* Person 클래스를 파라미터로 자동으로 생성할 수 있는 클래스 설정 (객체만 생성해주면 된다) */
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into person(name, age, address) values (:name, :age, :address)")
                .build();

        itemWriter.afterPropertiesSet();

        return itemWriter;
    }


    /**
     * reader
     * @return
     */
    private ItemReader<Person> itemReader() {
        return new CustomItemReader<>(getItems());
    }

    private List<Person> getItems() {
        List<Person> items = IntStream.range(0, 100)
                .mapToObj(i -> new Person(i + 1, "test name" + i, "test age", "test address"))
                .collect(Collectors.toList());

        return items;
    }
}
