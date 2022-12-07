package com.spring.batch.part5;

import com.spring.batch.part4.CustomItemReader;
import com.spring.batch.part4.Person;
import javassist.ClassPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class FlatItemReaderConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flatItemReaderJob() throws Exception {
        return this.jobBuilderFactory.get("flatItemReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(this.customFlatItemReaderStep())
                .next(this.csvFileStep()) /* 추가 */
                .build();
    }

    @Bean
    public Step customFlatItemReaderStep() {
        return this.stepBuilderFactory.get("customFlatItemReaderStep")
                .<Person, Person>chunk(10)
                .reader(new CustomItemReader<>(getItems()))
                //.processor()
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Step csvFileStep() throws Exception {
        return stepBuilderFactory.get("csvFileStep")
                .<Person, Person>chunk(10)
                .reader(this.csvFileItemReader())
                .writer(itemWriter())
                .build();
    }

    /**
     * FlatFileItemReader 사용한 csvFileItemReader
     * @return
     * @throws Exception
     */
    private FlatFileItemReader<Person> csvFileItemReader() throws Exception {
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        /* Person 의 필드명 설정 */
        tokenizer.setNames("id", "name", "age", "address");

        lineMapper.setLineTokenizer(tokenizer);

        lineMapper.setFieldSetMapper(fieldSet -> {
            int id = fieldSet.readInt("id");
            String name = fieldSet.readString("name");
            String age = fieldSet.readString("age");
            String address = fieldSet.readString("address");

            return new Person(id, name, age, address);
        });

        FlatFileItemReader<Person> itemReader = new FlatFileItemReaderBuilder<Person>()
                .name("csvFileItemReader")
                .encoding("UTF-8")
                .resource(new ClassPathResource("item5/test.csv"))
                .linesToSkip(1) /* 파일의 첫번째 row 는 필드명이므로 건너뛰겠다는 의미 */
                .lineMapper(lineMapper)
                .build();

        itemReader.afterPropertiesSet(); /* 설정값이 제대로 됬는지 검증 메서드 */

        return itemReader;
    }

    private ItemWriter<Person> itemWriter() {
        /* 일괄 처리 */
        return items -> log.info(items.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ")));
    }

    /**
     * 테스트 리스트 생성
     * @return
     */
    private List<Person> getItems() {
        List<Person> items = IntStream.range(0, 10)
                .mapToObj(i -> new Person(i + 1, "test name" + i, "test age", "test address"))
                .collect(Collectors.toList());

        return items;
    }
}
