package com.spring.batch._14_batch_초기화;

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
--job.name=initJob
 */

/**
 * Batch 초기화
 * 배치 초기화 작업이 완료되면 배치가 실행된다. -> 실행 주체 : jobLauncherApplicationRunner
 *
 * BatchProperties.java : application.yml 에 batch 관련 설정을 하여 이 설정들을 읽어온다.
 *
 *
 * 배치 실행
 * 1) jobLauncherApplicationRunner - run(String... args)
 * 2) jobLauncherApplicationRunner - launchJobFromProperties(properties)
 * 3) jobLauncherApplicationRunner - getJobParameters(properties)
 * 4) jobLauncherApplicationRunner - executeLocalJobs(jobParameters)
 * 5) jobLauncherApplicationRunner - executeRegisteredJobs(jobParameters)
 *
 *
 */
@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job initJob() {
        return this.jobBuilderFactory.get("initJob")
                /* step start */
                .start(initStep1())
                .next(initStep2())
                .build();
    }

    @Bean
    public Step initStep1() {
        return stepBuilderFactory.get("initStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("initStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step initStep2() {
        return stepBuilderFactory.get("initStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("initStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
