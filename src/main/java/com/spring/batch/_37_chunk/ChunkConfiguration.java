package com.spring.batch._37_chunk;

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
--job.name=chunkJob
 */
@Configuration
@RequiredArgsConstructor
public class ChunkConfiguration {
    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chunkJob() {
        return this.jobBuilderFactory.get("chunkJob")
                /* step start */
                .start(chunkStep1())
                .next(chunkStep2())
                .build();
    }

    @Bean
    public Step chunkStep1() {
        return stepBuilderFactory.get("chunkStep1")
                // ChunkOrientedTasklet.java (execute() 메서드)
                // SimpleChunkProvider.java
                // SimpleChunkProcessor.java (transform() : chunk 내부 데이터를 추출해서 가공작업 수행)
                // chunkProcessor.process (chunk 만큼 item 담는다.)
                // write() (전체 아이템 (List)를 보낸다.)
                // chunk 만큼 transaction
                .<String, String>chunk(5)
                .reader(new ListItemReader<>(Arrays.asList("item1", "item2", "item3", "item4", "item5")))
                .processor((ItemProcessor<String, String>) item -> {
                    Thread.sleep(300); // 0.3초 delay
                    System.out.println("item = " + item);
                    return "my" + item;
                })
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        // List type item
                        Thread.sleep(300); // 0.3초 delay
                        System.out.println("items = " + items);
                    }
                })
                .build();
    }

    @Bean
    public Step chunkStep2() {
        return stepBuilderFactory.get("chunkStep2")
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
