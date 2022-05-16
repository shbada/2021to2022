package com.spring.batch._23_taskletStep;

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
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/*
--job.name=taskletStepJob
 */

@Configuration
@RequiredArgsConstructor
public class TaskletStepConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job taskletStepJob() {
        return this.jobBuilderFactory.get("taskletStepJob")
                .start(taskletStepStep1())
                .next(chunkStep())
                .build();
    }

    /**
     * AbstractTaskletStepBuilder > TaskletStep 객체 생성
     * 부모 StepbuilderHelper.jaa 의 enhence() 호출
     * TsketStepBuilder 에 저장된 tasklet 을 저장시키고.
     *
     * 위 taskletStepJob()에서 build() 호출되면,
     * AbstractStep.java 의 execute() -> SimpleJob 이 실행시킴
     *
     * TaskletStep.java
     * @return
     */
    @Bean
    public Step taskletStepStep1() {
        return stepBuilderFactory.get("taskletStepStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("taskletStepStep1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    /* chunk 기반 */
    @Bean
    public Step chunkStep() {
        return stepBuilderFactory.get("chunkStep")
                .<String, String>chunk(3)
                /** reader */
                .reader(new ListItemReader<>(Arrays.asList("item1", "item2", "item3")))
                /** processor */
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String item) throws Exception {
                        return item.toUpperCase();
                    }
                })
                /** writer */
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        items.forEach(System.out::println);
                    }
                })
                .build();
    }
}
