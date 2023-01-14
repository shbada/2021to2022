package com.spring.batch._27_FlowJob;

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
--job.name=flowJob
 */
@Configuration
@RequiredArgsConstructor
public class FlowJobTestConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowTestJob() {
        return this.jobBuilderFactory.get("flowTestJob")
                /* 각각의 스텝은 독립적으로 생성되어 Tasklet 이 생성된다.
                 * Step 은 자기만의 Tasklet 을 가진다. */
                .start(flowTestStep1())
                // 위 스텝이 성공하게되면, step3 번을 실행시키고,
                // 위 스텝이 실패하게되면, step2 번을 실행시켜보자.
                .on("COMPLETED").to(flowTestStep3())
                .from(flowTestStep1())
                // STEP1 : FAILED, STEP2 : COMPLETED
                .on("FAILED").to(flowTestStep2()) // step1 이 실패했어도 JOB은 COMPLETED
                .end()
                .build();
    }

    @Bean
    public Step flowTestStep1() {
        return stepBuilderFactory.get("flowTestStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowTestStep1");

                        // 고의 에러 발생
                        throw new RuntimeException("step1 was failed");
                        // return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step flowTestStep2() {
        return stepBuilderFactory.get("flowSTesttep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowTestStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step flowTestStep3() {
        return stepBuilderFactory.get("flowTestStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowTestStep3");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}

