package com.spring.batch._32_decider;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=jobExecutionDeciderJob
 */

@Configuration
@RequiredArgsConstructor
public class JobExecutionDeciderConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobExecutionDeciderJob() {
        return this.jobBuilderFactory.get("jobExecutionDeciderJob")
                .incrementer(new RunIdIncrementer()) // job 여러번 실행 가능
                .start(deciderEvenStartStep())
                .next(decider())
                /* SimpleFlow */
                .from(decider()).on("ODD").to(deciderOddStep())
                .from(decider()).on("EVEN").to(deciderEvenStep())
                .end()
                .build();
    }

    /**
     * Decider Class
     * @return
     */
    @Bean
    public JobExecutionDecider decider() {
        return new CustomDecider();
    }

    @Bean
    public Step deciderEvenStartStep() {
        return stepBuilderFactory.get("deciderEvenStartStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("deciderEvenStartStep");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step deciderEvenStep() {
        return stepBuilderFactory.get("deciderEvenStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("deciderEvenStep");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step deciderOddStep() {
        return stepBuilderFactory.get("deciderOddStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("deciderOddStep");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
