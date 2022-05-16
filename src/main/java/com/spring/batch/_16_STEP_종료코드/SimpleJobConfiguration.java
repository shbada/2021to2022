package com.spring.batch._16_STEP_종료코드;

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
--job.name=simpleJob
 */

@Configuration
@RequiredArgsConstructor
public class SimpleJobConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() {
        return this.jobBuilderFactory.get("simpleJob")
                /* step start */
                .start(simpleStep1())
                .next(simpleStep2())
                .incrementer(new RunIdIncrementer())
                .validator(new JobParametersValidator() {
                    @Override
                    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {

                    }
                })
                .preventRestart()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {

                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {

                    }
                })
                .build();
    }

    @Bean
    public Step simpleStep1() {
        return stepBuilderFactory.get("simpleStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleStep2() {
        return stepBuilderFactory.get("simpleStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("simpleStep2");

                        // 마지막 STEP 상태값 강제 지정
                        // STATUS
                        chunkContext.getStepContext().getStepExecution().setStatus(BatchStatus.FAILED);
                        // EXIT_CODE
                        contribution.setExitStatus(ExitStatus.STOPPED); // 종료코드는 STOPPED

                        // 이때 BATCH_JOB_EXECUTION
                        // STATUS : FAILED
                        // EXIT_CODE : STOPPED
                        // 따라서 마지막(최종) STEP 의 상태값이 반영된다.
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
