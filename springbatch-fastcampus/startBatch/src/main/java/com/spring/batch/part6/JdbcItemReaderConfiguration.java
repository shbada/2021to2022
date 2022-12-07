package com.spring.batch.part6;

import com.spring.batch.part4.CustomItemReader;
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
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JdbcItemReaderConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource; /* h2 이므로 아무 설정없이 이렇게 빈 등록 가능 */

    @Bean
    public Job jdbcItemReaderJob() throws Exception {
        return this.jobBuilderFactory.get("jdbcItemReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(this.jdbcStep()) /* 추가 */
                .build();
    }


    @Bean
    public Step jdbcStep() throws Exception {
        return stepBuilderFactory.get("jdbcStep")
                .<Person, Person>chunk(10)
                .reader(this.jdbcCursorItemReader())
                .writer(itemWriter())
                .build();
    }

    /**
     * Cursor > jdbcCursorItemReader
     * @return
     * @throws Exception
     */
    private JdbcCursorItemReader<Person> jdbcCursorItemReader() throws Exception {
        JdbcCursorItemReader<Person> itemReader = new JdbcCursorItemReaderBuilder<Person>()
                .name("jdbcCursorItemReader")
                .dataSource(dataSource)
                .sql("select id, name, age, address from person")
                .rowMapper((rs, rowNum) ->
                    new Person(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4)
                ))
                .build();

        itemReader.afterPropertiesSet();

        return itemReader;
    }


    private ItemWriter<Person> itemWriter() {
        /* 일괄 처리 */
        return items -> log.info(items.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ")));
    }
}
