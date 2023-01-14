package com.spring.batch._22_StepBuilder;

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
import org.springframework.batch.item.*;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/*
--job.name=stepBuilderTestJob
 */

/**
 * StepBuilderFactory.java
 * -> StepBuilder 객체 리턴
 *
 * Tasklet -> StepBuilder.java - tasklet() 메서드 호출 -> TaskletStep 객체 생성
 *
 * 위 과정이 Step() 호출시마다 동일하게 반복. 근데 여기서 다른건
 *
 * 조건에 따라 하위 빌더들을 선택하게된다.
 *
 * 1) Tasklet : 위
 * 2) Chunk 기반 : SimpleStepBuilder 객체 생성
 * 3) partition -> PartitionStepBuilder - PartitionStep (job() 은 jobStepBuilder - jobStep)
 * 4) flow -> FlowStepBuilder - FlowStep
 *
 * 각각에 따라 다른 빌더를 생성한다.
 */
@Configuration
@RequiredArgsConstructor
public class StepBuilderConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepBuilderTestJob() {
        return this.jobBuilderFactory.get("stepBuilderTestJob")
                .start(stepBuilderStep1())
                .next(stepBuilderStep2())
                .next(stepBuilderStep3())
                .build();
    }

    @Bean
    public Step stepBuilderStep1() {
        return stepBuilderFactory.get("stepBuilderStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepBuilderStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step stepBuilderStep2() {
        return stepBuilderFactory.get("stepBuilderStep2")
                .<String, String>chunk(3)
                /** reader */
                .reader(new ItemReader<String>() {
                    @Override
                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                        return null;
                    }
                })
                /** processor */
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String s) throws Exception {
                        return null;
                    }
                })
                /** writer */
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> list) throws Exception {

                    }
                })
                .build();
    }

    @Bean
    public Step stepBuilderStep3() {
        return stepBuilderFactory.get("stepBuilderStep3")
                .partitioner(stepBuilderStep1())
                .gridSize(2)
                .build();
    }

    @Bean
    public Step stepBuilderStep4() {
        return stepBuilderFactory.get("stepBuilderStep4")
                .job(stepBuilderTestJob())
                .build();
    }

    @Bean
    public Step stepBuilderStep5() {
        return stepBuilderFactory.get("stepBuilderStep5")
                .flow(stepBuilderFlow())
                .build();
    }

    @Bean
    public Flow stepBuilderFlow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
        flowBuilder.start(stepBuilderStep2()).end();
        return flowBuilder.build();
    }
}
