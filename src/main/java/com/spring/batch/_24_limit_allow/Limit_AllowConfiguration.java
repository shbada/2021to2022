package com.spring.batch._24_limit_allow;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/*
--job.name=limitAllowStepJob
 */

@Configuration
@RequiredArgsConstructor
public class Limit_AllowConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job limitAllowStepJob() {
        return this.jobBuilderFactory.get("limitAllowStepJob")
                .start(limitAllowStepStep1())// COMPLETED (재시작에 포함되지 않음) -> allowStartIfComplete 추가 후 포함됨
                .next(limitAllowStepStep2())// FAILED (재시작의 대상)
                .build();
    }

    @Bean
    public Step limitAllowStepStep1() {
        return stepBuilderFactory.get("limitAllowStepStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("limitAllowStepStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .allowStartIfComplete(true) // COMPLETED 되도 재실행에 포함
                .build();
    }

    @Bean
    public Step limitAllowStepStep2() {
        return stepBuilderFactory.get("limitAllowStepStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("limitAllowStepStep2");
                        throw new RuntimeException("step2 was failed");
                        //return RepeatStatus.FINISHED;
                    }
                })
                .startLimit(2) // 해당 스텝의 실행은 2번까지만 가능, 3번부터 초과되어 오류 발생
                .build();
    }
}
