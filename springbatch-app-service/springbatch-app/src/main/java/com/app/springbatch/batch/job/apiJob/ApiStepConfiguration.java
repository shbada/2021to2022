package com.app.springbatch.batch.job.apiJob;

import com.app.springbatch.batch.chunk.processor.ApiItemProcessor1;
import com.app.springbatch.batch.chunk.processor.ApiItemProcessor2;
import com.app.springbatch.batch.chunk.processor.ApiItemProcessor3;
import com.app.springbatch.batch.chunk.processor.ProcessorClassifier;
import com.app.springbatch.batch.chunk.writer.ApiItemWriter1;
import com.app.springbatch.batch.chunk.writer.ApiItemWriter2;
import com.app.springbatch.batch.chunk.writer.ApiItemWriter3;
import com.app.springbatch.batch.chunk.writer.WriterClassifier;
import com.app.springbatch.batch.domain.ApiRequestVO;
import com.app.springbatch.batch.domain.ProductVO;
import com.app.springbatch.batch.partition.ProductPartitioner;
import com.app.springbatch.service.ApiService1;
import com.app.springbatch.service.ApiService2;
import com.app.springbatch.service.ApiService3;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ApiStepConfiguration {
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    private int chunkSize = 10;

    @Bean
    public Step apiMasterStep() throws Exception {
        return stepBuilderFactory.get("apiMasterStep")
                .partitioner(apiSlaveStep().getName(), partitioner())
                .step(apiSlaveStep())
                .gridSize(3)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Step apiSlaveStep() throws Exception {
        return stepBuilderFactory.get("apiSlaveStep")
                .<ProductVO, ProductVO>chunk(chunkSize)
                .reader(itemReader(null))
                /* 각 스레드가 자기의 해당 제품에 맞는 itemProcessor, itemWriter 을 호출한다. */
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public ProductPartitioner partitioner() {
        ProductPartitioner productPartitioner = new ProductPartitioner();
        productPartitioner.setDataSource(dataSource);

        return productPartitioner;
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3); // 기본 스레드 생성 수
        taskExecutor.setMaxPoolSize(6); // 최대 생성 가능 개수
        taskExecutor.setThreadNamePrefix("api-thread-");

        return taskExecutor;
    }

    @Bean
    @StepScope
    public ItemReader<ProductVO> itemReader(@Value("#{stepExecutionContext['product']}") ProductVO productVO) throws Exception {

        /*
           멀티스레드로 각각의 itemReader Class 들을 할당해야한다.
           파티셔닝에 멀티스레드로 StepExecution, ExecutionContext 클래스들이 만들어져서 각 스레드에 할당되어져있다.
           스레드별로 각각 StepExecution 을 가지고있다.

           type 별로 스레드를 만들고, type (1, 2, 3) 에 해당하는 데이터를 모두 가져와서
           type 에 해당하는 데이터를 파티셔닝한다.
         */
        JdbcPagingItemReader<ProductVO> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(dataSource);
        reader.setPageSize(chunkSize);
        reader.setRowMapper(new BeanPropertyRowMapper(ProductVO.class));

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, name, price, type");
        queryProvider.setFromClause("from product");
        queryProvider.setWhereClause("where type = :type");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("id", Order.DESCENDING);
        queryProvider.setSortKeys(sortKeys);

        reader.setParameterValues(QueryGenerator.getParameterForQuery("type", productVO.getType()));
        reader.setQueryProvider(queryProvider);
        reader.afterPropertiesSet();

        return reader;
    }

    /****************************************
     * type 1, 2, 3 별로 각 스레드가 돌고있고,
     * 각 스레드는 각각의 제품 정보를 가지고 itemWriter 로 전달한다.
     * 이 itemWriter 은 각각의 해당 API 서버로 호출하도록 한다.
     * 스레드 개수가 3개면 그 스레드는 각각의 itemReader, itemWriter 를 가질 수 있도록 구성한다.
     * 조건에 따라서 각 API 1, 2, 3 서버 중에 한 곳을 호출한다. (itemWriter <-> API 서버 호출)
     * **************************************
     */


    @Bean
    public ItemProcessor itemProcessor() {

        ClassifierCompositeItemProcessor<ProductVO, ApiRequestVO> processor = new ClassifierCompositeItemProcessor<>();

        ProcessorClassifier<ProductVO, ItemProcessor<?, ? extends ApiRequestVO>> classifier = new ProcessorClassifier();

        Map<String, ItemProcessor<ProductVO, ApiRequestVO>> processorMap = new HashMap<>();
        processorMap.put("1", new ApiItemProcessor1());
        processorMap.put("2", new ApiItemProcessor2());
        processorMap.put("3", new ApiItemProcessor3());

        classifier.setProcessorMap(processorMap);

        processor.setClassifier(classifier);

        return processor;
    }

    @Bean
    public ItemWriter itemWriter() {

        ClassifierCompositeItemWriter<ApiRequestVO> writer = new ClassifierCompositeItemWriter<>();

        WriterClassifier<ApiRequestVO, ItemWriter<? super ApiRequestVO>> classifier = new WriterClassifier();

        Map<String, ItemWriter<ApiRequestVO>> writerMap = new HashMap<>();
        writerMap.put("1", new ApiItemWriter1(new ApiService1()));
        writerMap.put("2", new ApiItemWriter2(new ApiService2()));
        writerMap.put("3", new ApiItemWriter3(new ApiService3()));

        classifier.setWriterMap(writerMap);

        writer.setClassifier(classifier);

        return writer;
    }

}
