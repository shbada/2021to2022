package com.spring.batch._29_BatchStatus_ExitStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
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
--job.name=batchStatusExistStatusJob
 */

@Configuration
@RequiredArgsConstructor
public class BatchStatusExitStatusConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchStatusExistStatusJob() {
        return this.jobBuilderFactory.get("batchStatusExistStatusJob")
                /**
                 * 1) 2개 모두 성공일때
                 * [STEP_EXECUTION]
                 * 1) EXIT_CODE : COMPLETED
                 * 2) STATUS (batchStatus) = COMPLETED
                 * [JOB_EXECUTION]
                 * 1) EXIT_CODE : COMPLETED
                 * 2) STATUS (batchStatus) = COMPLETED
                 *
                 * 2) STEP2 : contribution.setExitStatus(ExitStatus.FAILED);
                 * [STEP_EXECUTION]
                 * 1) EXIT_CODE : FAILED (STEP1 : COMPLETED, STEP2 : FAILED)
                 * 2) STATUS (batchStatus) = COMPLETED
                 * [JOB_EXECUTION]
                 * 1) JOB_EXECUTION 도 EXIT_CODE (FAILED)
                 * 2) STATUS(COMPLETED)
                 * -> 마지막 STEP의 EXIT_CODE 가 셋팅되었다.
                 * -> 마지막 STEP의 BATCH_STATUS가 셋팅되었다. (STEP2의 STATUS : COMPLETED)
                 */
                .start(batchStatusExistStatusStep1())// step1, step2
                .next(batchStatusExistStatusStep2())
                .build();
    }

    @Bean
    public Step batchStatusExistStatusStep1() {
        return stepBuilderFactory.get("batchStatusExistStatusStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("batchStatusExistStatusStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step batchStatusExistStatusStep2() {
        return stepBuilderFactory.get("batchStatusExistStatusStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("batchStatusExistStatusStep2");

                        // 고의 설정
                        // EXIT_STATUS : FAILED
                        // BATCH_STATUS : COMPLETED
                        contribution.setExitStatus(ExitStatus.FAILED);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}

