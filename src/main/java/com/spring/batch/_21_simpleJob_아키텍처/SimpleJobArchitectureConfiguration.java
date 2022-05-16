package com.spring.batch._21_simpleJob_아키텍처;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=simpleArchitectureJob
 */

/**
 * 단계별 디버깅
 * SimpleJob.java
 * AbstractJob.java
 * JobExecution.java
 * SimpleJobRepository.java
 *
 * jobInstance 생성되고,
 * 1) SimpleJobLauncher.java : run() > JobExecution 생성
 * 2) SimpleJobLauncher.java : createJobExecution()
 *    -> SimpleJobRepository.java (jobInstance 가 null 이면 생성, JobExecution 객체도 생성)
 *
 * 3) AbstractJob.java (doExecute 전에 Listener 의 beforeJob() 수행)
 * - doExecute - Step
 * - 그 후에 리스너의 AfterJob() 수행
 *
 */
@Configuration
@RequiredArgsConstructor
public class SimpleJobArchitectureConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleArchitectureJob() {
        return this.jobBuilderFactory.get("simpleArchitectureJob")
                /* step start */
                .incrementer(new RunIdIncrementer())
                .start(simpleArchitectureStep1())
                .next(simpleArchitectureStep2())
                .build();
    }

    @Bean
    public Step simpleArchitectureStep1() {
        return stepBuilderFactory.get("simpleArchitectureStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleArchitectureStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleArchitectureStep2() {
        return stepBuilderFactory.get("simpleArchitectureStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleArchitectureStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
