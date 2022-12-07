package com.spring.batch._45_partitioning;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class PartitioningConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                // masterStep 안에서 slaveStep 을 정의하면 된다.
                .start(masterStep())
                .build();
    }

    @Bean
    public Step masterStep() throws Exception {
        /*
           StepBuilder.java > partitional() 호출
           - slaveStep 을 만든다.
           masterStep 이 실행되고 있는 스텝
           PartitionStep (=masterStep의 역할을 한다)
           -> 여기서 각각의 스레드를 만들고, 각 스레드에 StepExecution 객체를 담는다.
           SimpleStepExecutionSplitter.java - getContexts()
           -> partitioner.partition(splitSize) 수행
           -> 위 메서드로부터 ColumnRangePartitioner 의 partition()이 실행되는 것이다.
           TaskExecutorPartitionHandler.java
           - createTask() - Callable 안의 call()이 수행됨
             이때 step.execute() -> step 이 TaskletStep 이고 이게 slaveStep 이다.
             여기 step 은 1개고, 스레드는 4개가 수행되는 것이다.
             step 은 멤버 변수라서 모든 스레드가 공유하는 변수다.
             대신 stepExecution 은 파라미터로 받아와서 각각 스레드별로 갖고있는 객체로, 공유되지 않는다.
             결국 DB에 저장되는건 stepExecution 이기때문에, 각각이 저장된다.
           AbstractStep - open()
           - slaveStep
           - 하나의 step, 여러개의 stepExecution (스레드별)
           - 여기서 이제 reader, writer이 수행되겠다.
           - open()을 통해서 itemStream 이 가지고있는 메서드를 호출한다.
           - ItemReader 의 open()이 호출된다.
            -> 여기서 stream 이 JdbcPagingItemReader 을 가지고있고, 이건 프록시 객체다. (@StepScope)
            -> open 메서드를 가지고 있는 실제 빈을 찾게되고, 실제 빈은 구동 시점이 아닌 실행 시점에 만들어지기 때문에,
               실제빈을 찾는데 없으면 생성한다. 현재는 StepScope.java 의 get()을 통해서 실제 빈이 생성된다.
            ** StepScope.java 의 get() 에서 scopedObject 가 null 인 상태
               - objectFactory.getObject(); : 실제 빈을 만드는 곳으로 간다. (PartitioningConfiguration - pagingItemReader())
               - 여기서 pagingItemReader()의 매개변수 minValue, maxValue 가 바인딩된다.
               - 그리고 이때 실제 빈이 생성되는 것이다.
               - 그 후로는 StepScope.step()이 수행되더라도 실제 빈이 있으므로 이 객체를 참조한다. 대신 이 실제 빈을 담는 객체는 각 스레드별이다.
               - 총 4개의 스레드니까, 프록시 객체는 1개고, 각 스레드별로 빈은 각각이기 때문에 스레드 안전하다.
            JdbcBatchItemWriter
            - itemWriter 도 마찬가지로, StepScope get()
            - 스레드마다 다른 JdbcBatchItemWriter 빈을 생성해서 실행시킨다.


            결과적으로 masterStep row DB 데이터는 slaveStep 여러개의 결과를 aggregate() 한 결과를 담는다.
         */
        return stepBuilderFactory.get("masterStep")
                // slaveStep 을 정의
                .partitioner(slaveStep().getName(), partitioner())
                .step(slaveStep())
                .gridSize(4)// 4개의 slave step 역할을 할 stepExecution 을 만들것
                .taskExecutor(new SimpleAsyncTaskExecutor()) // multi thread
                .build();
    }

    @Bean
    public Step slaveStep() {
        return stepBuilderFactory.get("slaveStep")
                .<Customer, Customer>chunk(1000)
                .reader(pagingItemReader(null, null))
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public ColumnRangePartitioner partitioner() {
        ColumnRangePartitioner columnRangePartitioner = new ColumnRangePartitioner();

        columnRangePartitioner.setColumn("id"); // 구분자 컬럼명
        columnRangePartitioner.setDataSource(this.dataSource);
        columnRangePartitioner.setTable("customer"); // 테이블명

        return columnRangePartitioner;
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<Customer> pagingItemReader(
            // runtime 때의 Job Parameter
            // 각각의 스레드에 StepExecution 이 있을것이고, 이 안에 ExecutionContext 가 있을것
            @Value("#{stepExecutionContext['minValue']}")Long minValue,
            @Value("#{stepExecutionContext['maxValue']}")Long maxValue) {
        System.out.println("reading " + minValue + " to " + maxValue);
        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setFetchSize(1000);
        reader.setRowMapper(new CustomerRowMapper());

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, firstName, lastName, birthdate");
        queryProvider.setFromClause("from customer");
        // 각 스레드별로 조건 (스레드마다 다른 id의 데이터를 읽어올 수 있도록 구성한다)
        queryProvider.setWhereClause("where id >= " + minValue + " and id <= " + maxValue);

        Map<String, Order> sortKeys = new HashMap<>(1);

        sortKeys.put("id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);

        reader.setQueryProvider(queryProvider);

        return reader;
    }

    @Bean
    @StepScope // 각 스레드마다 런타임 시점에 각각의 itemWriter 를 생성해서 할당
    // 하나의 스레드가 입력해도 상관은 없는데, 만약 데이터가 작으면 main 스레드가 작업해도 상관은 없음
    // 우선 @StepScope 를 선언하여 ItemWriter 도 여러 스레드로 수행하자.
    public JdbcBatchItemWriter<Customer> customerItemWriter() {
        JdbcBatchItemWriter<Customer> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(this.dataSource);
        itemWriter.setSql("insert into customer2 values (:id, :firstName, :lastName, :birthdate)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }


}
