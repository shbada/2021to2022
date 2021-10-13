package com.example.membergradebatch.batch;

import com.example.membergradebatch.entity.User;
import com.example.membergradebatch.repository.UserRepository;
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
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class UserConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final UserRepository userRepository;
    private final EntityManagerFactory entityManagerFactory;

    /**
     * --job.name=userJob
     * @return
     */
    @Bean
    public Job userJob() throws Exception {
        return this.jobBuilderFactory.get("userJob")
                .incrementer(new RunIdIncrementer())
                .start(this.userStep())
                .next(this.userLevelUpStep())
                .listener(new LevelUpJobExecutionListener(userRepository))
                .build();
    }

    @Bean
    public Step userStep() {
        return this.stepBuilderFactory.get("userStep")
                /* user Data 400 개 생성 */
                .tasklet(new SaveUserTasklet(userRepository))
                .build();
    }

    @Bean
    public Step userLevelUpStep() throws Exception {
        return this.stepBuilderFactory.get("userLevelUpStep")
                .<User, User>chunk(100)
                .reader(this.itemReader())
                .processor(this.itemProcessor())
                .writer(this.itemWriter())
                .build();
    }

    private ItemWriter<? super User> itemWriter() {
        return users -> {
            users.forEach(x -> {
                x.levelUp();
                userRepository.save(x);
                log.info("id: " + x.getId());
            });
        };
    }

    private ItemProcessor<? super User,? extends User> itemProcessor() {
        /* 등급 상향 유저 대상 추출 */
        return user -> {
            if (user.availableLevelUp()) { /* 등급 상향 유저 여부 체크 */
                log.info("user update target id: " + user.getId());
                return user;
            }

            return null;
        };
    }

    private ItemReader<? extends User> itemReader() throws Exception {
        /* JpaPagingItemReaderBuilder */
        JpaPagingItemReader<User> itemReader = new JpaPagingItemReaderBuilder<User>()
                .queryString("select u from User u")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(100) /* 정크 사이즈와 동일하게 보통 설정함. */
                .name("userItemReader")
                .build();

        itemReader.afterPropertiesSet();
        return itemReader;

    }
}
