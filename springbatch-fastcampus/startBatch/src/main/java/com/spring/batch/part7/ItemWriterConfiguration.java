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
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ItemWriterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemWriterJob() throws Exception {
        return this.jobBuilderFactory.get("itemWriterJob")
                .incrementer(new RunIdIncrementer())
                .start(this.csvItemWriterStep())
                .build();
    }

    @Bean
    public Step csvItemWriterStep() throws Exception {
        return this.stepBuilderFactory.get("csvItemWriterStep")
                .<Person, Person>chunk(10)
                .reader(this.itemReader())
                //.processor()
                .writer(this.csvFileItemWriter())
                .build();
    }

    /**
     * writer
     * @return
     */
    private ItemWriter<? super Person> csvFileItemWriter() throws Exception {
        BeanWrapperFieldExtractor<Person> fieldExtractor = new BeanWrapperFieldExtractor<>();

        /* 필드명 설정 */
        fieldExtractor.setNames(new String[]{"id", "name", "age", "address"});

        DelimitedLineAggregator<Person> lineAggregator = new DelimitedLineAggregator<>();

        /* csv 파일 , 기준으로 설정 */
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(fieldExtractor);

        FlatFileItemWriter<Person> itemWriter = new FlatFileItemWriterBuilder<Person>()
                .name("csvFileItemWriter")
                .encoding("UTF-8")
                /* 경로가 동일하면 생성된 파일을 덮어씀 (해당 파일에 데이터를 추가 생성하고 싶다면 아래 .append 참고) */
                .resource(new FileSystemResource("item7/test-output.csv")) /* FileSystemResource 로 경로 설정 */
                .lineAggregator(lineAggregator)
                /* 헤더 설정 */
                .headerCallback(writer -> writer.write("id, 이름, 나이, 거주지"))
                /* 푸터 설정 */
                //.footerCallback(writer -> writer.write("------------------"))
                .footerCallback(writer -> writer.write("------------------\n")) /* 개행문자 추가버전 : 파일에 추가 데이터 생성*/
                /* 파일 덮어씌지않고 해당 파일에 추가되는것으로 설정하기 */
                .append(true) // 또한 footer 에 개행문자를 추가해줘야한다.
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
