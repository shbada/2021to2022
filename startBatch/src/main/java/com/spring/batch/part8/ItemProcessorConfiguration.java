package com.spring.batch.part8;

import com.spring.batch.part4.CustomItemReader;
import com.spring.batch.part4.Person;
import com.spring.batch.part9.SavePersonJobListener;
import com.spring.batch.part9.SavePersonStepListener;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ItemProcessorConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemProcessorJob() throws Exception {
        return this.jobBuilderFactory.get("itemProcessorJob")
                .incrementer(new RunIdIncrementer())
                .start(this.itemProcessorStep())
                /* Job 리스너 등록 (순서대로 실행) : part9 */
                .listener(new SavePersonJobListener.SavePersonJobExecutionListener())
                .listener(new SavePersonJobListener.SavePersonAnnotationJobExecutionListener())
                .build();
    }

    @Bean
    public Step itemProcessorStep() throws Exception {
        return this.stepBuilderFactory.get("itemProcessorStep")
                .<Person, Person>chunk(10)
                .reader(this.itemReader())
                .processor(this.itemProcessor())
                .writer(this.itemWriter())
                /* Step 리스너 등록 (순서대로 실행) : part9 */
                .listener(new SavePersonStepListener.SavePersonStepExecutionListener())
                .listener(new SavePersonStepListener.SavePersonAnnotationStepExecutionListener())
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
    private ItemProcessor<? super Person,? extends Person> itemProcessor() {
        return item -> {
            if (item.getId() % 2 == 0) {
                return item;
            }

            return null;
        };
    }

    /**
     * reader
     * @return
     */
    private ItemReader<Person> itemReader() {
        return new CustomItemReader<>(getItems());
    }

    private List<Person> getItems() {
        List<Person> items = IntStream.range(0, 10)
                .mapToObj(i -> new Person(i + 1, "test name" + i, "test age", "test address"))
                .collect(Collectors.toList());

        return items;
    }
}
