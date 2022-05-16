package com.spring.batch._18_validator;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=validatorJob
 */

@Configuration
@RequiredArgsConstructor
public class ValidatorConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job validatorJob() {
        return this.jobBuilderFactory.get("validatorJob")
                /* step start */
                .start(validatorStep1())
                .next(validatorStep2())
                //.validator(new CustomJobParametersValidator())
                .validator(
                        // 1번째 인자 : requiredKeys
                        // 2번째 인자 : optionalKeys
                        new DefaultJobParametersValidator(new String[]{"name", "date"}, new String[]{"count"}))
                .build();
    }

    @Bean
    public Step validatorStep1() {
        return stepBuilderFactory.get("validatorStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("validatorStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step validatorStep2() {
        return stepBuilderFactory.get("validatorStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("validatorStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
