package com.spring.batch._53_reader_jdbcCursor;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class JdbcCursorConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

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
                .<Customer, Customer>chunk(10)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public JdbcCursorItemReader<Customer> customItemReader() {
        /*
        JdbcCursorItemReaderBuilder > build() -> JdbcCursorItemReader 생성
        (ItemStream)
        AbstractCursorItemReader > openCursor() - executeQuery()
        AbstractItemContingItemStreamReader > update() - 상태정보 기록

        AbstractCursorItemReader > doRead() - fetchSize가 10이므로 resultSet 에 10개가 담겨져있다.
        rs.next()로 row 를 읽어온다. (readCursor - row를 가져와서 객체에 매핑한다.)
        chunkSize 만큼 읽어오면 writer 로 수행
         */
        return new JdbcCursorItemReaderBuilder()
                .name("jdbcCursorItemReader")
                .fetchSize(10)
                .sql("select id, firstName, lastName, birthdate from customer where firstName like ? order by lastName, firstName")
                .beanRowMapper(Customer.class) // 자동으로 객체와 매핑
                .queryArguments("A%")
                .maxItemCount(3) // 조회할 최대 아이템 수
                .currentItemCount(2) // 조회 Item의 시작 시점
                .maxRows(100) // ResultSet이 포함할 수 있는 최대 row 수
                .dataSource(dataSource)
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
