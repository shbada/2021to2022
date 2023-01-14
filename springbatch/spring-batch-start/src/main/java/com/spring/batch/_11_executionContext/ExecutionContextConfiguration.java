package com.spring.batch._11_executionContext;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=executionContextTestJob
 */
@Configuration
@RequiredArgsConstructor
public class ExecutionContextConfiguration {
    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    /* tasklet */
    private final ExecutionContextTasklet1 executionContextTasklet1;
    private final ExecutionContextTasklet2 executionContextTasklet2;
    private final ExecutionContextTasklet3 executionContextTasklet3;
    private final ExecutionContextTasklet4 executionContextTasklet4;

    @Bean
    public Job executionContextTestJob() {
        return this.jobBuilderFactory.get("executionContextTestJob")
                .start(executionContextTestStep1())
                .next(executionContextTestStep2())
                .next(executionContextTestStep3())
                .next(executionContextTestStep4())
                .build();
    }

    @Bean
    public Step executionContextTestStep1() {
        return stepBuilderFactory.get("executionContextTestStep1")
                .tasklet(executionContextTasklet1)
                .build();
    }

    @Bean
    public Step executionContextTestStep2() {
        return stepBuilderFactory.get("executionContextTestStep2")
                .tasklet(executionContextTasklet2)
                .build();
    }

    @Bean
    public Step executionContextTestStep3() {
        return stepBuilderFactory.get("executionContextTestStep3")
                .tasklet(executionContextTasklet3)
                .build();
    }

    @Bean
    public Step executionContextTestStep4() {
        return stepBuilderFactory.get("executionContextTestStep4")
                .tasklet(executionContextTasklet4)
                .build();
    }
}
