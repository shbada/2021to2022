package com.spring.batch._36_jobScope;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=jobScopeStepScope2Job
 */
@RequiredArgsConstructor
@Configuration // 빈 생성을 위해
public class JobScope_StepScope_2_Configuration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobScopeStepScope2Job() {
        return this.jobBuilderFactory.get("jobScopeStepScope2Job")
                // .start 실행 시점에 Step Bean 생성 (@JobScope 가 있으므로 프록시 객체로 설정되고)
                // 실제 실행이 될때 실제 bean 이 생성됨 (getLastStepExecution(jobInstance, step.getName())
                // step.getName() 시점에 생성됨

                // JobScope.java 에서 getContext() : jobContext 가져오기

                .start(jobScopeStepScope2Step1(null))
                .next(jobScopeStepScope2Step2())
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        jobExecution.getExecutionContext().putString("name", "user1");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {

                    }
                })
                .build();
    }

    @Bean
    @JobScope
    public Step jobScopeStepScope2Step1(@Value("#{jobParameters['message']}") String message) {
        System.out.println("message = " + message);

        return stepBuilderFactory.get("jobScopeStepScope2Step1")
                .tasklet(jobScopeStepScope2Tasklet1(null))
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        stepExecution.getExecutionContext().putString("name2",  "user");
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return null;
                    }
                })
                .build();
    }

    @StepScope
    public Tasklet jobScopeStepScope2Tasklet1(@Value("#{jobExecutionContext['name']}") String name) { // 런타임에 참조 가능
        System.out.println("name = " + name); // .putString("name", "user1");

        return (stepContribution, chunkContext) -> {
            System.out.println("tasklet1 has executed");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step jobScopeStepScope2Step2() {
        return stepBuilderFactory.get("jobScopeStepScope2Step2")
                .tasklet(jobScopeStepScope2Tasklet1(null))
                .build();
    }

    @StepScope
    public Tasklet jobScopeStepScope2Tasklet2(@Value("#{stepExecutionContext['name2']}") String name2) {
        System.out.println("name2 = " + name2); // .putString("name2",  "user");

        return (stepContribution, chunkContext) -> {
            System.out.println("tasklet1 has executed");
            return RepeatStatus.FINISHED;
        };
    }
}