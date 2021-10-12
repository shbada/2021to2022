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
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ItemSkipConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemSkipJob() throws Exception {
        return this.jobBuilderFactory.get("itemSkipJob")
                .incrementer(new RunIdIncrementer())
                .start(this.itemSkipStep())
                /* Job 리스너 등록 (순서대로 실행) : part9 */
                .listener(new SavePersonJobListener.SavePersonJobExecutionListener())
                .listener(new SavePersonJobListener.SavePersonAnnotationJobExecutionListener())
                .build();
    }

    @Bean
    public Step itemSkipStep() throws Exception {
        return this.stepBuilderFactory.get("itemSkipStep")
                .<Person, Person>chunk(10)
                .reader(this.itemReader())
                .processor(this.itemProcessor())
                .writer(this.itemWriter())
                /* Step 리스너 등록 (순서대로 실행) : part9 */
                .listener(new SavePersonStepListener.SavePersonStepExecutionListener())
                .listener(new SavePersonStepListener.SavePersonAnnotationStepExecutionListener())
                .faultTolerant() /* 메소드를 호출시 예외처리할 수 있는 메서드를 선언할 수 있다 (skip 등) */
                .skip(NotFoundNameException.class)
                .skipLimit(3) /* 3번까지 NotFoundNameException 을 허용하겠다는 의미 */
                /**
                 * skipLimit(2) 일때 NotFoundNameException 가 3번 발생하면 실패.
                 * 로그에는 에러 로그가 남고, 데이터는 정상적으로 3개가 저장된다.
                 * 그리고 BATCH_STEP_EXECUTION 테이블에 STEP 결과코드를 조회하면 FAILED 로 남게된다.
                 * BATCH_JOB_EXECUTION 테이블에도 JOB 결과 코드가 FAILED 로 남게된다. (에러로그와 함께)
                 */
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
        // 만약 2개의 프로세서를 묶어야한다면
//        CompositeItemWriter<Object> build = new CompositeItemWriterBuilder<>()
//                .delegates(itemProcessor(), itemProcessor())
//                .build();

        return item -> {
            if (item.isNotEmptyName()) {
                return item;
            }

            throw new NotFoundNameException();
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
                .mapToObj(i -> {
                    Person person = null;

                    if (i % 2 == 0) {
                        person = new Person(i + 1, "", "test age", "test address");
                    } else {
                        person = new Person(i + 1, "test name" + i, "test age", "test address");
                    }
                    return person;
                })
                .collect(Collectors.toList());

        return items;
    }
}
