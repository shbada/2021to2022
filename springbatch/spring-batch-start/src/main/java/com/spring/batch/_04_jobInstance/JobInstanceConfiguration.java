package com.spring.batch._04_jobInstance;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
--job.name=jobInstanceTestJob
 */
@Configuration
@RequiredArgsConstructor
public class JobInstanceConfiguration {
    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobInstanceTestJob() {
        return this.jobBuilderFactory.get("jobInstanceTestJob")
                /* step start */
                .start(jobInstanceTestStep1())
                .next(jobInstanceTestStep2())
                .build();
    }

    @Bean
    public Step jobInstanceTestStep1() {
        return stepBuilderFactory.get("jobInstanceTestStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        /**
                         * Tasklet 은 무한반복되어 FINISHED 를 리턴하여 종료
                         */
                        System.out.println("jobInstanceTestStep1 was executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step jobInstanceTestStep2() {
        return stepBuilderFactory.get("jobInstanceTestStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("jobInstanceTestStep2 was executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
