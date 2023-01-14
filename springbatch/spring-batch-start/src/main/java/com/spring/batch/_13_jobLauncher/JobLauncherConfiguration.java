package com.spring.batch._13_jobLauncher;

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
--job.name=launcherTestJob
 */
/**
 1) BatchAutoConfiguration
 - JobLauncherApplicationRunner 가 jobLauncher 을 가지고있다.
 - JobLauncherApplicationRunner 는 ApplicationRunner 을 구현하고있으므로,
   스프링부트가 초기화가 완료가되면 해당 클래스의 run 메서드를 실행한다.
 - 최종적으로 execute() 로 와서 jobLauncher 을 실행시킨다. (jobLauncher.run(job, parameter))

 */
@Configuration
@RequiredArgsConstructor
public class JobLauncherConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job launcherTestJob() {
        return this.jobBuilderFactory.get("launcherTestJob")
                /* step start */
                .start(launcherTestStep1())
                .next(launcherTestStep2())
                .build();
    }

    @Bean
    public Step launcherTestStep1() {
        return stepBuilderFactory.get("launcherTestStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("launcherTestStep1");

                        // 동기 : 잡이 모두 끝난 후 결과 리턴
                        // 비동기 : 일단 결과를 내려주고, 내부적으로 배치 실행
                        Thread.sleep(3000);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step launcherTestStep2() {
        return stepBuilderFactory.get("launcherTestStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("launcherTestStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
