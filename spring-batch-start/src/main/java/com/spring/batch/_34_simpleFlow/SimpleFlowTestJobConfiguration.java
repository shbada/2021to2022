package com.spring.batch._34_simpleFlow;

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
--job.name=simpleFlowTestJob
 */

@Configuration
@RequiredArgsConstructor
public class SimpleFlowTestJobConfiguration {
    /**
     * flow1 -> step1, step2
     * step3
     * "COMPLETED" -> flow2 -> flow3 (step3, step4) -> step5, step6
     * "FAILED" -> flow3 (step3, step4)
     */

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    // flow job 구성
    @Bean
    public Job simpleFlowTestJob() {
        return this.jobBuilderFactory.get("simpleFlowTestJob")
                .start(simpleTestFlow())
                    .on("COMPLETED")
                    .to(simpleFlow2())
                .from(simpleTestFlow()) // 이 경우의 JOB (BATCH_STATUS : COMPLETED, EXIT_CODE : FAILED)
                    // flow 내 Step 이 실행되고 나서 ExitSTauts 를 FlowExecutionStatus 로 저장하고,
                    // 이가 FlowJob 의 배치 결과에 관여
                    .on("FAILED")
                    .to(simpleFlow3())
                .end()
                .build();
    }

    @Bean
    public Flow simpleTestFlow() {
        // flowBuilder
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("simpleTestFlow");
        flowBuilder.start(simpleFlowTestStep1())
                .next(simpleFlowTestStep2())
                .end();

        return flowBuilder.build();
    }

    @Bean
    public Flow simpleFlow2() {
        // flowBuilder
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("simpleFlow2");
        flowBuilder.start(simpleFlow3())
                .next(simpleFlowTestStep5())
                .next(simpleFlowTestStep6())
                .end();

        return flowBuilder.build();
    }

    @Bean
    public Flow simpleFlow3() {
        // flowBuilder
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("simpleFlow3");
        flowBuilder.start(simpleFlowTestStep3())
                .next(simpleFlowTestStep4())
                .end();

        return flowBuilder.build();
    }

    @Bean
    public Step simpleFlowTestStep1() {
        return stepBuilderFactory.get("simpleFlowTestStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowTestStep1");

                        // 고의 에러 발생
                        throw new RuntimeException("step1 was failed");
                        // return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleFlowTestStep2() {
        return stepBuilderFactory.get("simpleFlowTestStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowTestStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleFlowTestStep3() {
        return stepBuilderFactory.get("simpleFlowTestStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowTestStep3");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleFlowTestStep4() {
        return stepBuilderFactory.get("simpleFlowTestStep4")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowTestStep4");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleFlowTestStep5() {
        return stepBuilderFactory.get("simpleFlowTestStep5")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowTestStep5");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleFlowTestStep6() {
        return stepBuilderFactory.get("simpleFlowTestStep6")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowTestStep6");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}

