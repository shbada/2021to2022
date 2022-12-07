package com.spring.batch._07_jobExecution;

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
--job.name=JobExecutionTestJob
 */
/**
 * 1. Job 실행시키자.
 * Job이 COMPLETED 로 끝나면 BATCH_JOB_EXECUTION, BATCH_JOB_INSTANCE 각 1개의 row가 들어간다.
 * Job이 FAILED 로 끝나면 BATCH_JOB_INSTANCE 에는 1개의 row가 생겼고 BATCH_JOB_EXECUTION에도 1개의 row가
 * 생겼는데 상태값은 FAILED
 *
 * 2. 여기서 또 한번 실행해보자.
 * 그럼 여기서 JobInstnace 가 존재하기 때문에 에러가 발생할까? (같은 job, parameter)
 *
 * -> 예외가 발생하지않고 job이 다시 수행되었다.
 * -> BATCH_JOB_EXECUTION 에 추가 row 가 들어간다.
 * -> BATCH_JOB_INSATNCE 는 그대로 1개의 row를 가진다. (기존 객체를 사용한다)
 *
 * 3. 다시 성공으로 실행시켜보자.
 * -> BATCH_JOB_INSTNACE 는 역시나 그대로 1개의 row (기존 객체 재사용했으므로)
 * -> BATCH_JOB_EXECUTION 이 COMPLETED 로 새로 insert 되었다.
 * (여기서 이제 job 은 성공되었다.)
 *
 * 4. 여기서 또 한번 실행해보자.
 * 그럼 jobInstance 가 이미 존재한다는 에러가 발생한다.
 */
@Configuration
@RequiredArgsConstructor
public class JobExecutionConfiguration {
    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job JobExecutionTestJob() {
        return this.jobBuilderFactory.get("JobExecutionTestJob")
                /* step start */
                .start(JobExecutionTestStep1())
                .next(JobExecutionTestStep2())
                .build();
    }

    @Bean
    public Step JobExecutionTestStep1() {
        return stepBuilderFactory.get("JobExecutionTestStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("JobExecutionTestStep1 was executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step JobExecutionTestStep2() {
        return stepBuilderFactory.get("JobExecutionTestStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("JobExecutionTestStep2 was executed");
                        /** 에러 발생 */
                        throw new RuntimeException("step2 has failed"); // 에러 발생시키기
//                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
