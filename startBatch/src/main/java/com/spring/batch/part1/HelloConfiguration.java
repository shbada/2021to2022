package com.spring.batch.part1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * --spring.batch.job.names=helloJob
 * 위 설정을 쓰지않으면 모든 Batch 가 실행된다. -> application.yml 에 설정을 추가하자.
 * application.yml 에 spring:batch:job:names 추가되었다. 이후로는 아래와같이 설정하자.
 * --job.name=helloJob
 *
 * schema-db2.sql (배치 메타데이터 테이블 등)
 */
@Configuration
@Slf4j
public class HelloConfiguration {
    /* springBatch 설정에 의해 jobBuilderFactory 는 이미 빈으로 설정되어있으므로 생성자 주입으로 받을 수 있다.*/
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public HelloConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    /**
     * Job
     * @return
     */
    @Bean
    public Job helloJob() { /* Job : 배치 실행 단위 */
        return jobBuilderFactory.get("helloJob") /* 잡 이름 : helloWorld, 배치를 실행시키는 key */
                .incrementer(new RunIdIncrementer()) /* 항상 잡이 실행될때마다 파라미터ID를 자동으로 생성해주는 클래스 */
                .start(this.helloStep()) /* 잡에 필요한 스텝을 설정해야함 */
                .build();
    }

    /**
     * Step
     * @return
     */
    @Bean
    public Step helloStep() { /* tasklet 기반 */
        return stepBuilderFactory.get("helloStep") /* 스텝 이름 */
                .tasklet((contribution, chunkContext) -> {
                    log.info("hello spring batch");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
