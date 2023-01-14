package com.spring.batch.part9;

import com.spring.batch.part4.CustomItemReader;
import com.spring.batch.part4.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ItemRetryConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemRetryJob() throws Exception {
        return this.jobBuilderFactory.get("itemRetryJob")
                .incrementer(new RunIdIncrementer())
                .start(this.itemRetryStep())
                /* Job 리스너 등록 (순서대로 실행) : part9 */
                //.listener(new SavePersonJobListener.SavePersonJobExecutionListener())
                //.listener(new SavePersonJobListener.SavePersonAnnotationJobExecutionListener())
                .build();
    }

    @Bean
    public Step itemRetryStep() throws Exception {
        return this.stepBuilderFactory.get("itemRetryStep")
                .<Person, Person>chunk(10)
                .reader(this.itemReader())
                .processor(this.itemProcessor())
                .writer(this.itemWriter())
                /* Step 리스너 등록 (순서대로 실행) : part9 */
                //.listener(new SavePersonStepListener.SavePersonStepExecutionListener())
                //.listener(new SavePersonStepListener.SavePersonAnnotationStepExecutionListener())
//                .faultTolerant() /* 메소드를 호출시 예외처리할 수 있는 메서드를 선언할 수 있다 (skip, retry 등) */
//                .retry(NotFoundNameException.class)
//                .retryLimit(3)
                /* 위 방법 사용 가능, ItemValidationRetryProcessor.java 방법으로 사용 가능 */
                .build();
    }

    /**
     * writer
     * @return
     */
    private ItemWriter<? super Person> itemWriter() {
        return items -> items.forEach(x -> log.info("PERSON.ID : {}", x.getId()));
    }

    /**
     * processor
     * @return
     */
    private ItemProcessor<? super Person,? extends Person> itemProcessor() throws Exception {
        ItemProcessor<Person, Person> validationProcessor = item -> {
            if (item.isNotEmptyName()) {
                return item;
            }

            throw new NotFoundNameException();
        };

        CompositeItemProcessor<Person, Person> itemProcessor = new CompositeItemProcessorBuilder<Person, Person>()
                .delegates(new ItemValidationRetryProcessor(), validationProcessor)
                .build();

        itemProcessor.afterPropertiesSet();
        return itemProcessor;
    }

    /**
     * reader
     * @return
     */
    private ItemReader<Person> itemReader() {
        return new CustomItemReader<>(getItems());
    }

    private List<Person> getItems() {
        List<Person> items = IntStream.range(1, 11) // 1 ~ 10
                .mapToObj(i -> {
                    Person person = null;

                    if (i == 8 || i == 9 || i == 10) { // 3건만
                        person = new Person(i, "", "test age", "test address");
                    } else {
                        person = new Person(i, "test name" + i, "test age", "test address");
                    }
                    return person;
                })
                .collect(Collectors.toList());

        log.info(String.valueOf(items));
        return items;
    }
}
