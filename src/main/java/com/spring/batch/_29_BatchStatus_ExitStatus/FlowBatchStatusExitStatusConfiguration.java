package com.spring.batch._29_BatchStatus_ExitStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
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
--job.name=flowBatchStatusExistStatusJob
 */
@Configuration
@RequiredArgsConstructor
public class FlowBatchStatusExitStatusConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowBatchStatusExistStatusJob() {
        return this.jobBuilderFactory.get("flowBatchStatusExistStatusJob")
                .start(flowBatchStatusExistStatusStep1())
                // FAILED 로 끝나면, 그 다음 Step2 를 실행해라
                /**
                 * [JOB_EXECUTION]
                 * 1) STATUS : COMPLETED
                 * 2) EXIT_CODE : COMPLTED (STEP2가 성공했기 때문에 STEP1의 FAILED 가 아니다)
                 *
                 * [STEP_EXECUTION]
                 * 1) STATUS : STEP1(COMPLETED), STEP2(COMPLETED)
                 * 2) EXIT_CODE : STEP1(FAILED), STEP2(COMPLETED)
                 */
                .on("FAILED") // FAILED 되도록 설정함
                .to(flowBatchStatusExistStatusStep2())
                .end()
                .build();
    }

    @Bean
    public Step flowBatchStatusExistStatusStep1() {
        return stepBuilderFactory.get("flowBatchStatusExistStatusStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowBatchStatusExistStatusStep1");
                        // 고의 설정
                        contribution.setExitStatus(ExitStatus.FAILED);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step flowBatchStatusExistStatusStep2() {
        return stepBuilderFactory.get("flowBatchStatusExistStatusStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowBatchStatusExistStatusStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}

