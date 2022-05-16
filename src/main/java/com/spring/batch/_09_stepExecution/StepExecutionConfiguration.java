package com.spring.batch._09_stepExecution;

import com.spring.batch._08_step.CustomTasklet;
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
--job.name=stepExecutionTestJob
 */
/**
 * SimpleStepHandler.java
 * AbstractStep.java
 * TaskletStep.java
 */
@Configuration
@RequiredArgsConstructor
public class StepExecutionConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepExecutionTestJob() {
        return this.jobBuilderFactory.get("stepExecutionTestJob")
                /**
                 * StepExecution 은 총 3개 생성되겠다. (Step이 3개이므로)
                 * 각 스텝별 상태를 저장한다. (BATCH_STEP_EXECUTION)
                 * 3개의 StepExecution 의 부모 JobExecution 은 현재의 jobExecution 1개로 동일하다.
                 */
                .start(executionTestStep1())
                .next(executionTestStep2())
                .next(executionTestStep3())
                .build();
    }

    @Bean
    public Step executionTestStep1() {
        return stepBuilderFactory.get("executionTestStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("executionTestStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step executionTestStep2() {
        return stepBuilderFactory.get("executionTestStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("executionTestStep1");
                        /* 고의로 에러 발생 */
                        /**
                         * jobExecution 은 FAILED 겠다. 해당 스텝이 실패했으므로.
                         * StepExecution 은 2개만 생성됨.
                         * Step 시작 시점에 생성되므로 현재의 스텝이 실패했어도 생성은 되었다. 대신, 상태값이 FAILED
                         * Step3 에 대한 StepExecution 은 생성되지 않았다.
                         * BATCH_STEP_EXECUTION 에 EXIT_CODE, EXIT_MESSAGE 로 오류 관련 정보를 확인할 수 있다.
                         *
                         * 해당 잡은 Step 중 1개가 실패됬으므로 잡 재실행이 가능하다.
                         * 다시 해당 잡을 실행해보자.
                         *
                         * 해당 스텝은 다시 성공 코드로 변경하자.
                         * step1 은 수행하지 않고, step2~step3 가 실행되었다.
                         * 모두 성공했으므로 jobExecution 의 COMPLETED 로 새로운 row 1개가 insert 되었다.
                         *
                         * 재실행할때 성공했던 step1 도 실행하려면 옵션값을 설정해주면 된다.
                         */
                        throw new RuntimeException("step2 failed");
                        // return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step executionTestStep3() {
        return stepBuilderFactory.get("executionTestStep3")
                // 빈으로 등록해도되고, 이렇게 객체 생성해도 된다.
                .tasklet(new CustomTasklet())
                .build();
    }
}
