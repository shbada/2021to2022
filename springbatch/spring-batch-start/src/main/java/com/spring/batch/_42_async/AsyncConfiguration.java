 package com.spring.batch._42_async;

 import lombok.RequiredArgsConstructor;
 import org.springframework.batch.core.Job;
 import org.springframework.batch.core.Step;
 import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
 import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
 import org.springframework.batch.core.launch.support.RunIdIncrementer;
 import org.springframework.batch.integration.async.AsyncItemProcessor;
 import org.springframework.batch.integration.async.AsyncItemWriter;
 import org.springframework.batch.item.ItemProcessor;
 import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
 import org.springframework.batch.item.database.JdbcBatchItemWriter;
 import org.springframework.batch.item.database.JdbcPagingItemReader;
 import org.springframework.batch.item.database.Order;
 import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.core.task.SimpleAsyncTaskExecutor;

 import javax.sql.DataSource;
 import java.util.HashMap;
 import java.util.Map;

 /*
  --job.name=asyncJob
   */
 @Configuration
 @RequiredArgsConstructor
 public class AsyncConfiguration {
     // job 생성
     private final JobBuilderFactory jobBuilderFactory;
     private final StepBuilderFactory stepBuilderFactory;

     private final DataSource dataSource;

     @Bean
     public Job asyncJob() throws Exception {
         return this.jobBuilderFactory.get("asyncJob")
                 /* step start */
                 .incrementer(new RunIdIncrementer())
                 //.start(asyncStep1())
                 .start(asyncStep2())
                 .listener(new StopWatchJobListener())
                 .build();
     }

     /**
      * 동기 방식
      * 약간의 sleep 을 줬는데, 그만큼의 시간이 더 걸린다.
      * @return
      */
     @Bean
     public Step asyncStep1() {
         return stepBuilderFactory.get("asyncStep1")
                 .<Customer, Customer>chunk(100)
                 .reader(pagingItemReader())
                 .processor(customItemProcessor())
                 .writer(customItemWriter())
                 .build();
     }

     /**
      * 비동기 방식
      * @return
      * @throws Exception
      */
     @Bean
     public Step asyncStep2() throws Exception {
         return stepBuilderFactory.get("asyncStep2")
                 .<Customer, Customer>chunk(100)
                 .reader(pagingItemReader())
                 // AsyncItemProcessor.java - process()
                 // RunnableFuture - run() 호출
                 // Future : RUnnable 실행 이후 데이터를 반환받고자 할때, 반환되는 아이템을 품고있는 클래스
                 // -> 미래에 어떤 시점에 우리가 받고자하는 응답(데이터)을 받을 수 있음
                 // Callable - call() (안에서 process 처리)
                 // delegate.process(item)
                 // taskExecutor.execute(task); -> task를 실행하게되고, 이 task가 내부적으로 call()을 실행하게된다
                 // taskExecutor이 만든 별도의 스레드가 실행시킴 - 비동기적으로 (main 스레드와 별도로 실행되는것)

                 // AsyncItemWriter : List 에 Future 가 담겨져있고, Future 안에 item 이 있음
                 // Future 안에 있는걸 list 꺼내 담아서, 이를 write(list)로 넘긴다.
                 // FutureTask get() - 완료될때까지 대기한다.
                 // 아이템을 다 받아지면 (완료가 되면) 여기서 list.add()를 하는것
                 // 최종적으로 ItemWriter로 전달해서 list는 이제 ArrayList 타입으로 된다.
                 //
                 .processor(asyncItemProcessor())
                 .writer(asyncItemWriter())
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

     /**
      * 동기 Processor
      * @return
      */
     @Bean
     public ItemProcessor customItemProcessor() {
         return new ItemProcessor<Customer, Customer>() {
             @Override
             public Customer process(Customer item) throws Exception {

                 // 동기 - 4초
                 // 비동기 - 0.03
                 Thread.sleep(30); // 0.01초

                 return new Customer(item.getId(),
                         item.getFirstName().toUpperCase(),
                         item.getLastName().toUpperCase(),
                         item.getBirthdate());
             }
         };
     }

     /**
      * 동기 Writer
      * @return
      */
     @Bean
     public JdbcBatchItemWriter customItemWriter() {
         JdbcBatchItemWriter<Customer> itemWriter = new JdbcBatchItemWriter<>();

         itemWriter.setDataSource(this.dataSource);
         itemWriter.setSql("insert into customer2 values (:id, :firstName, :lastName, :birthdate)");
         itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
         itemWriter.afterPropertiesSet();

         return itemWriter;
     }

     /**
      * 비동기 Processor
      * @return
      * @throws Exception
      */
     @Bean
     public AsyncItemProcessor asyncItemProcessor() throws Exception {
         AsyncItemProcessor<Customer, Customer> asyncItemProcessor = new AsyncItemProcessor();

         asyncItemProcessor.setDelegate(customItemProcessor()); // 위임
         asyncItemProcessor.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        asyncItemProcessor.setTaskExecutor(taskExecutor());
//         asyncItemProcessor.afterPropertiesSet(); // 빈으로 사용하지 않으면 이 메서드를 호출해줘야한다.

         return asyncItemProcessor;
     }

     /**
      * 비동기 Writer
      * @return
      * @throws Exception
      */
     @Bean
     public AsyncItemWriter asyncItemWriter() throws Exception {
         AsyncItemWriter<Customer> asyncItemWriter = new AsyncItemWriter<>();

         asyncItemWriter.setDelegate(customItemWriter());
//         asyncItemWriter.afterPropertiesSet(); // 빈으로 사용하지 않으면 이 메서드를 호출해줘야한다.

         return asyncItemWriter;
     }
 }
