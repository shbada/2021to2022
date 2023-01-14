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
--job.name=simpleFlowArchitectureJob
 */

@Configuration
@RequiredArgsConstructor
public class SimpleFlowArchitectureConfiguration {
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
    public Job simpleFlowArchitectureJob() {
        return this.jobBuilderFactory.get("simpleFlowArchitectureJob")
                // StepState (.start, .on 이 모두 실행이 되면 .to )
                .start(simpleFlowArchitectureStep1())
                    .on("COMPLETED")
                    // addTransition(FlowBuilder.java) : pattern("COMPLETED"), next(step2다) (index 0부터 시작하므로 step1으로 보인다)
                    .to(simpleFlowArchitectureStep2())
                .from(simpleFlowArchitectureStep1())
                    .on("FAILED")
                    .to(simpleFlow1())
                // SimpleFlow 객체 생성
                // 이때 위에서 set 된 정보로, step 을 수행한다.
                // startState, transitionMap(state, next)
                .end()
                .build();
    }

    @Bean
    public Flow simpleFlow1() {
        // flowBuilder
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("simpleFlow1");
        flowBuilder.start(simpleFlowArchitectureStep2())
                .next(simpleFlowArchitectureStep3())
                .end();

        return flowBuilder.build();
    }

    @Bean
    public Step simpleFlowArchitectureStep1() {
        return stepBuilderFactory.get("simpleFlowArchitectureStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowArchitectureStep1");

                        // 고의 에러 발생
                        throw new RuntimeException("step1 was failed");
                        // return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleFlowArchitectureStep2() {
        return stepBuilderFactory.get("simpleFlowArchitectureStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowArchitectureStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleFlowArchitectureStep3() {
        return stepBuilderFactory.get("simpleFlowArchitectureStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowArchitectureStep3");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}

