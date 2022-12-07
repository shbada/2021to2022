package com.spring.batch._35_flowStep;

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
--job.name=flowStepJob
 */

@Configuration
@RequiredArgsConstructor
public class FlowStepConfiguration {
    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    // flow job 구성
    @Bean
    public Job flowStepJob() {
        return this.jobBuilderFactory.get("flowStepJob")
                .start(flowTestStep())
                .next(flowStepStep2())
                .build();
    }

    private Step flowTestStep() {
        return stepBuilderFactory.get("flowTestStep")
                .flow(flowStepFlow())
                .build();
    }

    @Bean
    public Flow flowStepFlow() {
        // flowBuilder
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowStepFlow");
        flowBuilder.start(flowStepStep1())
                .end();

        return flowBuilder.build();
    }

    @Bean
    public Step flowStepStep1() {
        return stepBuilderFactory.get("flowStepStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowStepStep1");

                        /**
                         * 상태값이 어떻게 저장될까?
                         * JOB_EXECUTION (STATUS 'FAILED', EXIT_CODE 'FAILED')
                         * STEP_EXECUTION (STATUS 'FAILED', EXIT_CODE 'FAILED')
                         * stpe1 을 품고있는 flowStep 도 실패하게되고, job 도 이 상태값을 따라간다.
                         *
                         */
                        throw new RuntimeException("step1 was failed");
                        // return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step flowStepStep2() {
        return stepBuilderFactory.get("flowStepStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowStepStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}

