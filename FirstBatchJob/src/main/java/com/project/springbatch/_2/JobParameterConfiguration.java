package com.project.springbatch._2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

/*
--job.name=jobParameterTestJob --job.name=jobParameterTestJob -name=user1 -seq(long)=2L -date(date)=2021/01/01 -double(double)=16.5
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class JobParameterConfiguration {
    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobParameterTestJob() {
        return this.jobBuilderFactory.get("jobParameterTestJob")
                /* step start */
                .start(jobParameterTestStep1())
                .next(jobParameterTestStep2())
                .build();
    }

    @Bean
    public Step jobParameterTestStep1() {
        return stepBuilderFactory.get("jobParameterTestStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("jobParameterTestStep1 was executed");

                        /* CASE1. contribution */
                        JobParameters jobParameters = contribution.getStepExecution()
                                .getJobExecution()
                                .getJobParameters();

                        String name = jobParameters.getString("name");
                        Long seq = jobParameters.getLong("seq");
                        Date date = jobParameters.getDate("date");
                        Double doubleParam = jobParameters.getDouble("double");

                        /* CASE2. chunkContext */
                        Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();
                        String name2 = (String) jobParameters1.get("name");

                        log.info("name : " + name);
                        log.info("seq : " + seq);
                        log.info("date : " + date);
                        log.info("doubleParam : " + doubleParam);
                        log.info("name2 : " + name2);

                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step jobParameterTestStep2() {
        return stepBuilderFactory.get("jobParameterTestStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("jobParameterTestStep2 was executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
