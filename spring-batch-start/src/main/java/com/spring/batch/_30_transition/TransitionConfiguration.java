package com.spring.batch._30_transition;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=transitionJob
 */

@Configuration
@RequiredArgsConstructor
public class TransitionConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job transitionJob() {
        return this.jobBuilderFactory.get("transitionJob")
                // step1 이 FAILED 면 step2 를 실행
                // step2 가 FAILED 면 stop
                .start(transitionStep1())
                    .on("FAILED")
                    .to(transitionStep2())
                    .on("FAILED")
                    .stop()
                // step1이 실행되었을때 "FAILED"가 아닌 (위의 조건) 모든 경우 step3 실행
                // next step4 실행
                .from(transitionStep1())
                    .on("*")
                    .to(transitionStep3())
                    .next(transitionStep4())
                // step2 종료 이후, "FAILED" 외의 어떤 값이 오더라도 step5로 이동
                .from(transitionStep2())
                    .on("*")
                    .to(transitionStep5())
                    .end()
                .build();
    }

    @Bean
    public Step transitionStep1() {
        return stepBuilderFactory.get("transitionStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("transitionStep1");
                        // FAILED
                        contribution.setExitStatus(ExitStatus.FAILED);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step transitionStep2() {
        return stepBuilderFactory.get("transitionStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("transitionStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step transitionStep3() {
        return stepBuilderFactory.get("transitionStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("transitionStep3");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step transitionStep4() {
        return stepBuilderFactory.get("transitionStep4")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("transitionStep4");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step transitionStep5() {
        return stepBuilderFactory.get("transitionStep5")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("transitionStep5");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}

