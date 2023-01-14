package com.spring.batch._19_preventRestart;

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
--job.name=preventRestartJob
 */

@Configuration
@RequiredArgsConstructor
public class PreventRestartJobConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job preventRestartJob() {
        return this.jobBuilderFactory.get("preventRestartJob")
                /* step start */
                .start(preventRestartStep1())
                .next(preventRestartStep2())
                /**
                 * JobBuilderHelper.java
                 * 의 CommonJobProperties : Job의 속성들을 저장하는 값 (restartable) 필드에 값을 저장한다. (true/false)
                 * AbstractJob 에도 restartable 이 있다. properties 에 저장된 위 값을 이때 저장한다.
                 * 그래서 SimpleJob 이 이 값을 사용한다.
                 */
                .preventRestart() // default : true, preventRestart() : false, preventRestart(false) : false
                .build();
    }

    @Bean
    public Step preventRestartStep1() {
        return stepBuilderFactory.get("preventRestartStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("preventRestartStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step preventRestartStep2() {
        return stepBuilderFactory.get("preventRestartStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("preventRestartStep2");

                        throw new RuntimeException("step2 fail");

                        //return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
