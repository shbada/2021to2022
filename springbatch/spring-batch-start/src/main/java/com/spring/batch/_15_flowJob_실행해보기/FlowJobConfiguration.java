package com.spring.batch._15_flowJob_실행해보기;

import lombok.RequiredArgsConstructor;
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
--job.name=flowJob
 */

/**
 * Batch 초기화
 * 배치 초기화 작업이 완료되면 배치가 실행된다. -> 실행 주체 : jobLauncherApplicationRunner
 *
 * BatchProperties.java : application.yml 에 batch 관련 설정을 하여 이 설정들을 읽어온다.
 *
 *
 * 배치 실행
 * 1) jobLauncherApplicationRunner - run(String... args)
 * 2) jobLauncherApplicationRunner - launchJobFromProperties(properties)
 * 3) jobLauncherApplicationRunner - getJobParameters(properties)
 * 4) jobLauncherApplicationRunner - executeLocalJobs(jobParameters)
 * 5) jobLauncherApplicationRunner - executeRegisteredJobs(jobParameters)
 *
 *
 */
@Configuration
@RequiredArgsConstructor
public class FlowJobConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

//    @Bean
//    public Job initJob() {
//        return this.jobBuilderFactory.get("initJob")
//                /* step start */
//                .start(initStep1())
//                .next(initStep2())
//                .build();
//    }

    @Bean
    public Job flowJob() {
        return this.jobBuilderFactory.get("flowJob")
                /* step start */
                .start(flow()) // Flow 객체를 함께 전달 : jobBuilder의 start():FlowJobBuilder 를 생성한다. -> 그리고 내부적으로 start(flow)해서 JobFlowBuilder 를 생성한다.
                .next(flowStep3())
                .end()
                .build();
    }

//    @Bean
//    public Step initStep1() {
//        return stepBuilderFactory.get("initStep1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("initStep1");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Step initStep2() {
//        return stepBuilderFactory.get("initStep2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("initStep2");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }

    /**
     * FlowJob
     * @return
     */
    @Bean
    public Flow flow() {
        // "flow"
        // step 과 동일한 레벨로, 잡 안에 스텝이 있고 SimpleJob 안에서 스텝이 포함되어 실행되듯이,
        // FlowJob 안에 flow 가 포함되어 실행된다.
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
        flowBuilder.start(flowStep1())
                   .next(flowStep2())
                   .end();

        return flowBuilder.build();
    }

    @Bean
    public Step flowStep1() {
        return stepBuilderFactory.get("flowStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step flowStep2() {
        return stepBuilderFactory.get("flowStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowStep2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step flowStep3() {
        return stepBuilderFactory.get("flowStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowStep3");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
