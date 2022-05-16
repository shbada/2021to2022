package com.spring.batch._26_TaskletStep_아키텍처;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/*
--job.name=taskletArchitectureStepJob
 */

@Configuration
@RequiredArgsConstructor
public class TaskletStepArchitectureConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job taskletArchitectureStepJob() {
        return this.jobBuilderFactory.get("taskletArchitectureStepJob")
                .start(taskletArchitectureStepStep1())
                .listener(new StepExecutionListener() {
                    // before
                    @Override
                    public void beforeStep(StepExecution stepExecution) {

                    }
                    // after
                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return null;
                    }
                })
                .build();
    }

    @Bean
    public Step taskletArchitectureStepStep1() {
        return stepBuilderFactory.get("taskletArchitectureStepStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("taskletArchitectureStepStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
