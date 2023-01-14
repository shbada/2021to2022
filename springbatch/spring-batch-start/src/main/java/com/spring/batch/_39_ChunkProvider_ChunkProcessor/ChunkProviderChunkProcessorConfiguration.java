package com.spring.batch._39_ChunkProvider_ChunkProcessor;

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
--job.name=chunkProviderChunkProcessorJob
 */
@Configuration
@RequiredArgsConstructor
public class ChunkProviderChunkProcessorConfiguration {
    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chunkProviderChunkProcessorJob() {
        return this.jobBuilderFactory.get("chunkProviderChunkProcessorJob")
                /* step start */
                .start(chunkProviderChunkProcessorStep1())
                .next(chunkProviderChunkProcessorStep2())
                .build();
    }

    @Bean
    public Step chunkProviderChunkProcessorStep1() {
        return stepBuilderFactory.get("chunkProviderChunkProcessorStep1")
                // TaskletStep.java 의 doExecute() (stepOperations.iterate -> 반복 실행)
                // RepeatTemplate doInChunkContext()
                // SimpleChunkProvider - read() -> ItemReader (chunk 만큼 list add)
                // 넘어갈때마다 chunk list reset
                // SimpleChunkProcessor - process() -> ItemProcessor (chunk 만큼 가공하여 list add)
                // SImpleChunkProcessor - write()
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
    public Step chunkProviderChunkProcessorStep2() {
        return stepBuilderFactory.get("chunkProviderChunkProcessorStep2")
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
