package com.spring.batch._38_ChunkOriented;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/*
--job.name=chunkOrientedTaskletJob
 */
@Configuration
@RequiredArgsConstructor
public class ChunkOrientedTaskletConfiguration {
    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chunkOrientedTaskletJob() {
        return this.jobBuilderFactory.get("chunkOrientedTaskletJob")
                /* step start */
                .start(chunkOrientedTaskletStep1())
                .next(chunkOrientedTaskletStep2())
                .build();
    }

    @Bean
    public Step chunkOrientedTaskletStep1() {
        return stepBuilderFactory.get("chunkOrientedTaskletStep1")
                // SimpleStepBuilder -> createTasklet (ChunkOrientedTasklet 을 만든다)
                // SimpleChunkProvider(ItemReader), SimpleChunkProcessor(ItemProcessor, ItemWriter)
                // 2 개만큼 list 를 담고, 이를 processor에 넘긴다.
                // processor 도 하나씩 수행하여 2개를 완료하면 wirter 로 간다.
                // writer 에는 2개가 들어있는 list 를 받는다.
                // 끝나면 다시 과정 수행 (ChunkOrientedTasklet 의 execute())
                .<String, String>chunk(2)
                .reader(new ListItemReader<>(Arrays.asList("item1", "item2", "item3", "item4", "item5", "item6")))
                .processor((ItemProcessor<String, String>) item -> {
                    System.out.println("item = " + item);
                    return "my_" + item;
                })
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        items.forEach(System.out::println);
                    }
                })
                .build();
    }

    @Bean
    public Step chunkOrientedTaskletStep2() {
        return stepBuilderFactory.get("chunkOrientedTaskletStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("chunkJobConfiguration step2 was executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
