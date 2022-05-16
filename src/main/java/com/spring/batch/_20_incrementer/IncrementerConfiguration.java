package com.spring.batch._20_incrementer;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=incrementerJob
 */

/**
 * JobParametersIncrementer.java - getNext()
 */
@Configuration
@RequiredArgsConstructor
public class IncrementerConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job incrementerJob() {
        return this.jobBuilderFactory.get("incrementerJob")
                /* step start */
                .start(incrementerStep1())
                .next(incrementerStep2())
                // 기존 구현체
//                .incrementer(new RunIdIncrementer())
                // 커스텀 가능
                // 항상 변하는 값 이 생겼다. JOB 을 성공해도 해당 JOB은 계속 실행이 가능하다.
                // 계속 변하는 값 + 동일한 파라미터 -> JobParameters 가 되므로 다른걸로 인식된다.
                .incrementer(new CustomJobParametersIncrementer())
                .build();
    }

    @Bean
    public Step incrementerStep1() {
        return stepBuilderFactory.get("incrementerStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("incrementerStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step incrementerStep2() {
        return stepBuilderFactory.get("incrementerStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("incrementerStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
