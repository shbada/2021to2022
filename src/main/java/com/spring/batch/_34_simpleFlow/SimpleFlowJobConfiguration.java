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
--job.name=simpleFlowJob
 */

/**
 * JsrFlow (JAVA 기본 배치에 제공됬던 flow 을 스프링 배치가 제공하기 위해 제공)
 *
 * SimpleFlow (이게 중요함)
 * -  State 를 가지고있다
 * - SimpleFlow 가 State 를 실행시키고, (State 는 그 안에 내부적으로 flow, step 등의 가지고있는 객체들을 실행시킨다)
 *
 * FlowBuilder - createState() =Step, JobExecutionDecider, Flow 에 따라 수행
 * state 실행(state.handle())
 * 이때 FlowState.java 로 오고, 여기서 flow.start(executor)...
 * 모든 Step, Flow 가 수행된 후 전체적인 수행이 끝남
 *
 */
@Configuration
@RequiredArgsConstructor
public class SimpleFlowJobConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    // flow job 구성
    @Bean
    public Job simpleFlowJob() {
        return this.jobBuilderFactory.get("simpleFlowJob")
                // simpleFlow 생성 (simpleFlow 안에 simpleFlow()인 또다른 simpleFlow 가 포함되어있음)
                .start(simpleFlow())
                .start(simpleFlowStep3())
                .end()
                .build();
    }

    @Bean
    public Flow simpleFlow() {
        // flowBuilder
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("simpleFlow");
        flowBuilder.start(simpleFlowStep1())
                .next(simpleFlowStep2())
                .end();

        return flowBuilder.build();
    }

    @Bean
    public Step simpleFlowStep1() {
        return stepBuilderFactory.get("simpleFlowStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowStep1");

                        // 고의 에러 발생
                        throw new RuntimeException("step1 was failed");
                        // return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleFlowStep2() {
        return stepBuilderFactory.get("simpleFlowStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleFlowStep3() {
        return stepBuilderFactory.get("simpleFlowStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleFlowStep3");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}

