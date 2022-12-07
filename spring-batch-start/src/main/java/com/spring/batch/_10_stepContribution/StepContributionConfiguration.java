package com.spring.batch._10_stepContribution;

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
--job.name=stepContributionTestJob
 */
@Configuration
@RequiredArgsConstructor
public class StepContributionConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepContributionTestJob() {
        return this.jobBuilderFactory.get("stepContributionTestJob")
                .start(contributionTestStep1())
                .next(contributionTestStep2())
                .build();
    }

    @Bean
    public Step contributionTestStep1() {
        return stepBuilderFactory.get("contributionTestStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        /* contribution 의 정보를 마지막에 StepExecution 에 apply() 해준다. */
                        System.out.println(contribution.getExitStatus());
                        System.out.println(contribution.getReadCount());

                        System.out.println("contributionTestStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step contributionTestStep2() {
        return stepBuilderFactory.get("contributionTestStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("contributionTestStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
