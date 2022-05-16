package com.spring.batch._43_multiThread;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.*;

@RequiredArgsConstructor
@Configuration
public class MultiThreadStepConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .listener(new StopWatchJobListener())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(100)
                .reader(pagingItemReader())
                .listener(new CustomReadListener())
                .processor((ItemProcessor<Customer, Customer>) item -> item)
                .listener(new CustomProcessListener())
                .writer(customItemWriter())
                .listener(new CustomWriteListener())
                /** multi thread 설정 */
                // SimpleAsyncTaskExecutor 을 스프링배치가 제공해주고있는데, 이는 비동기적 실행을 제공해준다.
                // ThreadPoolTaskExecutor는 상속받는 클래스들이 여러개 있고,
                // 내부적으로 ThreadPoolExecutor(자바에서 제공해줌- threadpool 관리 가능)
                // 이 한줄만 추가하면 비동기적인 멀티스레드로 수행되게한다.
                // 아래 주석처리하면 main 스레드가 chunk개수 만큼 수행한다. (약 1초)
                // 아래 주석처리를 해제하고 비동기적 멀티 스레드로 처리한다면?
                // async-thread1, thread2, thread3, thread4 각 스레드가 멀티스레드로 수행된다.
                // 0.5초 (동기때보타 절반정도 줄어든다)
                // ItemReader 만! 데이터가 겹치지 않도록 동기화처리가 되고있다.
                // -> JdbcCusorItemReader - readCursor 자체는 동기가 안되기 때문에 customItemReader을 사용하면 데이터 문제가 발생할 수도 있다.
                .taskExecutor(taskExecutor())
//                .throttleLimit(2)
                .build();
    }

    /**
     * JdbcCusorItemReader - readCursor 자체는 동기가 안되기 때문에 customItemReader을 사용하면 데이터 문제가 발생할 수도 있다.
     * customItemReader을 사용하면 중복데이터가 발생해버림.. -> 근데 자체에서 오류뱉음
     * 이럴땐 JdbcPagingItemReader을 써야함 (동기적 구현)
     * ItemReader 은 동기적 구현을 꼭 해야한다. custom으로 생성할때에도 주의해야한다
     * @return
     */
    @Bean
    public JdbcCursorItemReader<Customer> customItemReader() {
        return new JdbcCursorItemReaderBuilder()
                .name("jdbcCursorItemReader")
                .fetchSize(100)
                .sql("select id, firstName, lastName, birthdate from customer order by id")
                .beanRowMapper(Customer.class)
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JdbcPagingItemReader<Customer> pagingItemReader() {
        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setPageSize(100);
        reader.setRowMapper(new CustomerRowMapper());

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, firstName, lastName, birthdate");
        queryProvider.setFromClause("from customer");

        Map<String, Order> sortKeys = new HashMap<>(1);

        sortKeys.put("id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);

        reader.setQueryProvider(queryProvider);

        return reader;
    }

    @Bean
    public JdbcBatchItemWriter customItemWriter() {
        JdbcBatchItemWriter<Customer> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(this.dataSource);
        itemWriter.setSql("insert into customer2 values (:id, :firstName, :lastName, :birthdate)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4); // 몇개의 스레드를 관리할것인지?
        executor.setMaxPoolSize(8); // 4개의 스레드가 작업을 처리하고 있는데, 나머지 처리되지 않은 task가 있을때 얼마만큼의 최대 스레드를 생성할것인지?
        executor.setThreadNamePrefix("async-thread-");
        return executor;
    }
}
