package com.project.springbatch._12_jobRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
--job.name=jobRepositoryTestJob -requestDate=20220526
 */

/**
 * SimpleStepHandler.java
 * AbstractStep.java
 * TaskletStep.java
 */
@Configuration
@RequiredArgsConstructor
public class JobRepositoryConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    // listener
    private final JobRepositoryListener jobRepositoryListener;

    @Bean
    public Job jobRepositoryTestJob() {
        return this.jobBuilderFactory.get("jobRepositoryTestJob")
                .start(jobRepositoryStep1())
                .next(jobRepositoryStep2())
                .listener(jobRepositoryListener) // 리스너 적용
                .build();
    }

    @Bean
    public Step jobRepositoryStep1() {
        return stepBuilderFactory.get("jobRepositoryStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("jobRepositoryStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step jobRepositoryStep2() {
        return stepBuilderFactory.get("jobRepositoryStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("jobRepositoryStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
