package com.spring.batch._31_custom_exitStatus;

import com.spring.batch._08_step.CustomTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
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
--job.name=customExitStatusJob
 */

@Configuration
@RequiredArgsConstructor
public class CustomExitStatusConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job customExitStatusJob() {
        return this.jobBuilderFactory.get("customExitStatusJob")
                .start(customExitStatusStep1())
                    .on("FAILED")
                    // STEP2 COMPLETED 일때 JOB : FAILED(STATUS), FAILED(EXIT_STATUS)
                    .to(customExitStatusStep2())
                    // 여길 만족하지 못했다.
                    // 그래서 JOB 이 FAILED 이다.
                    // 결국 STEP2 가 FAILED 라, JOB 이 FAILED STATUS 인 것이다.
                    // PASS 를 정의해보자. (new PassCheckingListener)
                    // STEP2 (STATUS:COMPLETED, EXIT_CODE:PASS)
                    // JOB (STATUS:STOPPED, EXIT_CODE:STOPPED)
                    .on("PASS") // 사용자 코드
                    .stop()
                .end()
                .build();
    }

    @Bean
    public Step customExitStatusStep1() {
        return stepBuilderFactory.get("customExitStatusStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("customExitStatusStep1");
                        // FAILED 설정
                        contribution.getStepExecution().setExitStatus(ExitStatus.FAILED);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step customExitStatusStep2() {
        return stepBuilderFactory.get("customExitStatusStep2")
                // 빈으로 등록해도되고, 이렇게 객체 생성해도 된다.
                .tasklet(new CustomTasklet())
                .listener(new PassCheckingListener())
                .build();
    }
}
