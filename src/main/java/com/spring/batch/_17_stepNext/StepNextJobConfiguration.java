package com.spring.batch._17_stepNext;

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
--job.name=stepNextJob
 */

@Configuration
@RequiredArgsConstructor
public class StepNextJobConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextJob() {
        return this.jobBuilderFactory.get("stepNextJob")
                /* step start */
                .start(stepNextStep1())
                .next(stepNextStep2())
                .build(); // SimpleJob 생성 (step 을 실행하게된다) (SimpleJob, FlowJob 가능)
    }

    @Bean
    public Step stepNextStep1() {
        return stepBuilderFactory.get("stepNextStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepNextStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step stepNextStep2() {
        return stepBuilderFactory.get("stepNextStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepNextStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
