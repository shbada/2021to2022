package com.spring.batch.part2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class SharedConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job sharedJob() { /* Job : 배치 실행 단위 */
        return jobBuilderFactory.get("sharedJob") /* 잡 이름 : helloWorld, 배치를 실행시키는 key */
                .incrementer(new RunIdIncrementer()) /* 항상 잡이 실행될때마다 파라미터ID를 자동으로 생성해주는 클래스 */
                .start(this.sharedStep())
                .next(this.shareStep2())
                .build();
    }

    /**
     * sharedStep : pushkey
     * @return
     */
    @Bean
    public Step sharedStep() { /* tasklet 기반 */
        return stepBuilderFactory.get("sharedStep") /* 스텝 이름 */
                .tasklet((contribution, chunkContext) -> {
                    /**
                     * JobExecution : Job 내에서 데이터 공유
                     * StepExecution : Step 내에서 데이터 공유
                     * (Step 내에서 데이터 공유의 의미 : 하나의 스텝에서 공유한다는 의미, 여러 다른 Step 공유가 아님)
                     * (JobExecution가 Job 내에서 공유가 가능하므로 이게 Step 끼리 공유 가능하다는 것이다)
                     */
                    /* StepExecution */
                    StepExecution stepExecution = contribution.getStepExecution(); // contribution 에서 꺼낸다.
                    ExecutionContext stepExecutionContext = stepExecution.getExecutionContext();
                    stepExecutionContext.putString("stepKey", "step execution context");

                    /* JObExecution */
                    JobExecution jobExecution = stepExecution.getJobExecution(); // StepExecution 에서 꺼낸다.
                    JobInstance jobInstance = jobExecution.getJobInstance(); // jobExecution 에서 꺼낸다.
                    ExecutionContext jobExecutionContext = jobExecution.getExecutionContext(); // jobExecution 에서 꺼낸다.
                    jobExecutionContext.putString("jobKey", "job execution context");

                    JobParameters jobParameters = jobExecution.getJobParameters();

                    //  jobName : sharedJob, stepName : sharedStep, parameter : 2
                    log.info("jobName : {}, stepName : {}, parameter : {}",
                            jobInstance.getJobName(),
                            stepExecution.getStepName(),
                            jobParameters.getLong("run.id"));

                    return RepeatStatus.FINISHED;
                }).build();
    }

    /**
     * sharedStep2 : getKey
     * @return
     */
    @Bean
    public Step shareStep2() { /* tasklet 기반 */
        return stepBuilderFactory.get("shareStep2") /* 스텝 이름 */
                .tasklet((contribution, chunkContext) -> {
                    /* StepExecution */
                    StepExecution stepExecution = contribution.getStepExecution(); // contribution 에서 꺼낸다.
                    ExecutionContext stepExecutionContext = stepExecution.getExecutionContext();

                    /* JObExecution */
                    JobExecution jobExecution = stepExecution.getJobExecution(); // StepExecution 에서 꺼낸다.
                    ExecutionContext jobExecutionContext = jobExecution.getExecutionContext(); // jobExecution 에서 꺼낸다.

                    // jobKey : job execution context, stepKey : emptyStepKey
                    // stepKey : emptyStepKey : step 끼리 공유되는 데이터가 아니다.
                    log.info("jobKey : {}, stepKey : {}",
                            jobExecutionContext.getString("jobKey", "emptyJobKey"),
                            stepExecutionContext.getString("stepKey", "emptyStepKey"));

                    return RepeatStatus.FINISHED;
                }).build();
    }
}
