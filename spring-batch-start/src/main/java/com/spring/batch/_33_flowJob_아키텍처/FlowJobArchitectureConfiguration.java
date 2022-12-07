package com.spring.batch._33_flowJob_아키텍처;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=flowJobArchitectureJob
 */

/**
 * FlowBuilder.java 의 next()
 * SimpleFlow 객체 생성
 * start(job) : JobBuilder.java - jobFlowBuilder > FlowJobBuilder
 */
@Configuration
@RequiredArgsConstructor
public class FlowJobArchitectureConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowJobArchitectureJob() {
        return this.jobBuilderFactory.get("flowJobArchitectureJob")
                .start(flowJobArchitectureFlowJob()) // flow return
                .next(flowJobArchitectureStep3())
                .end()
                .build();
    }

    @Bean
    public Flow flowJobArchitectureFlowJob() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowJobArchitectureFlowJob");
        flowBuilder.start(flowJobArchitectureStep1()) // step1
                    .next(flowJobArchitectureStep2()) // step2
                    .end();

        return flowBuilder.build();
    }

    @Bean
    public Step flowJobArchitectureStep1() {
        return stepBuilderFactory.get("flowJobArchitectureStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowJobArchitectureStep1");
                        // FAILED
                        contribution.setExitStatus(ExitStatus.FAILED);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step flowJobArchitectureStep2() {
        return stepBuilderFactory.get("flowJobArchitectureStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowJobArchitectureStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step flowJobArchitectureStep3() {
        return stepBuilderFactory.get("flowJobArchitectureStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowJobArchitectureStep3");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}

