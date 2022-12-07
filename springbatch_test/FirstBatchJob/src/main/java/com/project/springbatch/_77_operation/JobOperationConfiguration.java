package com.project.springbatch._77_operation;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=operationTestBatchJob
 */
@RequiredArgsConstructor
@Configuration
public class JobOperationConfiguration {

    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;
    public final JobRegistry jobRegistry; // 스프링 배치가 초기화될 때 빈으로 등록

    @Bean
    public Job operationTestJob() throws Exception {
        return jobBuilderFactory.get("operationTestBatchJob")
                .incrementer(new RunIdIncrementer())
                .start(operationTestStep1())
                .next(operationTestStep2())
                .build();
    }

    @Bean
    public Step operationTestStep1() throws Exception {
        return stepBuilderFactory.get("operationTestStep1")
                .tasklet((contribution, chunkContext) ->
                        {
                            System.out.println("step1 was executed");
                            Thread.sleep(5000);
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }
    @Bean
    public Step operationTestStep2() throws Exception {
        return stepBuilderFactory.get("operationTestStep2")
                .tasklet((contribution, chunkContext) ->
                        {
                            System.out.println("step2 was executed");
                            Thread.sleep(5000);
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    /**
     * JobRegistryBeanPostProcessor
     */
    @Bean
    public BeanPostProcessor jobRegistryBeanPostProcessor() throws Exception {
        JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
        postProcessor.setJobRegistry(jobRegistry); // 빈 셋팅
        return postProcessor;
    }
}
