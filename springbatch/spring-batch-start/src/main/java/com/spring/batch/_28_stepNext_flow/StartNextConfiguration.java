package com.spring.batch._28_stepNext_flow;

import lombok.RequiredArgsConstructor;
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
--job.name=flowJob
 */

@Configuration
@RequiredArgsConstructor
public class StartNextConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job startNextJob() {
        return this.jobBuilderFactory.get("startNextJob")
                .start(flowA())// step1, step2
                .next(startNextStep3())
                .next(flowB()) // step4, step5
                .next(startNextStep6())
                .end()
                .build();
    }

    @Bean
    public Flow flowA() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowA");
        flowBuilder.start(startNextStep1())
                .next(startNextStep2())
                .end();

        return flowBuilder.build();
    }

    @Bean
    public Flow flowB() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowA");
        flowBuilder.start(startNextStep4())
                .next(startNextStep5())
                .end();

        return flowBuilder.build();
    }

    @Bean
    public Step startNextStep1() {
        return stepBuilderFactory.get("startNextStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step startNextStep2() {
        return stepBuilderFactory.get("startNextStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step startNextStep3() {
        return stepBuilderFactory.get("startNextStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep3");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step startNextStep4() {
        return stepBuilderFactory.get("startNextStep4")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep4");

                        // 고의 에러 발생
                        // JOB FAILED
                        throw new RuntimeException("step4 was failed");
                        // return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step startNextStep5() {
        return stepBuilderFactory.get("startNextStep5")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep5");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step startNextStep6() {
        return stepBuilderFactory.get("startNextStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep6");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}

