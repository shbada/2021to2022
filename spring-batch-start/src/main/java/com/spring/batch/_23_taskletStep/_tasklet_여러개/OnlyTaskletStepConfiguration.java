package com.spring.batch._23_taskletStep._tasklet_여러개;

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
--job.name=onlyTaskletJob
 */

@Configuration
@RequiredArgsConstructor
public class OnlyTaskletStepConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job onlyTaskletJob() {
        return this.jobBuilderFactory.get("onlyTaskletJob")
                .start(onlyTaskletStep1())
                .next(onlyTaskletStep2())
                .build();
    }

    @Bean
    public Step onlyTaskletStep1() {
        return stepBuilderFactory.get("onlyTaskletStep1")
                // TaskletStep - tasklet 저장
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("onlyTaskletStep1");

                        // 반환값
                        // NULL 이여도 FINISHED
                        // 그 외는 무한반복 - 주의 필요
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step onlyTaskletStep2() {
        return stepBuilderFactory.get("onlyTaskletStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("실행되면 안되요");
                        return RepeatStatus.FINISHED;
                    }
                })
                .tasklet(new OnlyTasklet()) // 여러개 불가능, 마지막 tasklet 실행
                .build();
    }
}
