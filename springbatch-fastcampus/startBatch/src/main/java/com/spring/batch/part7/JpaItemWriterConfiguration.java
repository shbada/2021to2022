package com.spring.batch.part7;

import com.spring.batch.part4.CustomItemReader;
import com.spring.batch.part4.Person;
import com.spring.batch.part6.PersonEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JpaItemWriterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job jpaItemWriterJob() throws Exception {
        return this.jobBuilderFactory.get("jpaItemWriterJob")
                .incrementer(new RunIdIncrementer())
                .start(this.jpaItemWriterStep())
                .build();
    }

    @Bean
    public Step jpaItemWriterStep() throws Exception {
        return this.stepBuilderFactory.get("jpaItemWriterStep")
                .<PersonEntity, PersonEntity>chunk(10)
                .reader(this.itemReader())
                //.processor()
                .writer(this.jpaBatchItemWriter())
                .build();
    }

    /**
     * jpa Writer
     * @return
     */
    private ItemWriter<? super PersonEntity> jpaBatchItemWriter() {
        /* insert 쿼리를 실행하면서 select 쿼리도 함께 실행된다. (아무런 설정없이 진행한다면 merge 가 수행된다: getItems()에서 id(pk)를 설정해줬기 때문) */
//        return new JpaItemWriterBuilder<PersonEntity>()
//                .entityManagerFactory(entityManagerFactory)
//                .build();
        /* merge 가 아닌 insert 만 수행되도록 설정하자 */
        return new JpaItemWriterBuilder<PersonEntity>()
                .entityManagerFactory(entityManagerFactory)
                /* 아래에서 id를 직접 설정해주면 userPersis(true)가 없어도 merge 로 실행되지않는다. 성능을 위한 선언 */
                .usePersist(true) /* 아래 getItems() 에서 id를 직접 설정해줘서 에러 발생 (변경 후 정상작동) */
                .build();
    }


    /**
     * reader
     * @return
     */
    private ItemReader<PersonEntity> itemReader() {
        return new CustomItemReader<>(getItems());
    }

    private List<PersonEntity> getItems() {
        List<PersonEntity> items = IntStream.range(0, 100)
                /* id를 자동으로 설정안해주고 있음 */
                // .mapToObj(i -> new PersonEntity(i + 1, "test name" + i, "test age", "test address"))
                /* 설정 안되도록 변경 */
                .mapToObj(i -> new PersonEntity("test name" + i, "test age", "test address"))
                .collect(Collectors.toList());

        return items;
    }
}
