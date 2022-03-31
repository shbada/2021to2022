package com.spring.batch.itemWriter.delimited;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class FlatFilesDelimitedWriteConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<String,String>chunk(1)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ListItemReader customItemReader() {

        List<Customer> customers = Arrays.asList(new Customer(1, "hong gil dong1", 41),
                new Customer(2, "hong gil dong2", 42),
                new Customer(3, "hong gil dong3", 43));

        ListItemReader<Customer> reader = new ListItemReader<>(customers);
        return reader;
    }

    @Bean
    public FlatFileItemWriter<Customer> customItemWriter() throws Exception {
        /*
           FlatFileItemWriter > doWriter()
           - items : Customer 객체의 리스트 (3개일때 총 3줄의 String)
           - 아이템 개수만큼 line 을 하나하나씩 작업한다.
           - lineAggregator.aggregate(item) -> delimitedLineAggregator 의 aggregate() 호출
           - 1) ExtractorLineAggregator (부모) 의 aggregate() -> 필드들을 꺼내와서 하나하나씩 배열에 저장한다.
           - 2) 그리고 다시 자식에게 전달한다. DelimitedLineAggregator(자식) 의 doAggregator() -> 배열을 구분자를 사용하여 하나의 문자열로 합친다.
           - 파일 생성

           - 만약, 다시 실행한다면?
           -> 실행은 되는데, 파일은 그대로다. 이때 .append(true)로 한다면, 해당 파일에 계속해서 append 된다.

           .shouldDeleteIfEmpty(true) : 만약 작업할 데이터가 없다면, 해당 파일을 삭제해라.
         */
        return new FlatFileItemWriterBuilder<Customer>()
                .name("customerWriter")
                .resource(new ClassPathResource("createWriter\\customer.csv"))
                //.append(true)
                .delimited()
                .delimiter(",")
                .names(new String[] {"id", "name", "age"})
                .build();
    }

    /*
    @Bean
    public FlatFileItemWriter<Customer> customItemWriter() throws Exception {
        BeanWrapperFieldExtractor<Customer> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"id","name","age"});
        fieldExtractor.afterPropertiesSet();

        DelimitedLineAggregator<Customer> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(fieldExtractor);

        return new FlatFileItemWriterBuilder<Customer>()
                .name("CustomerWriter")
                .resource(new ClassPathResource("customer.csv"))
                .lineAggregator(lineAggregator)
                .build();
    }
    */
}
