package com.spring.batch._40_item;

import com.spring.batch._40_item.custom.CustomItemProcessor;
import com.spring.batch._40_item.custom.CustomItemReader;
import com.spring.batch._40_item.custom.CustomItemWriter;
import com.spring.batch._40_item.custom.Customer;
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

import java.util.Arrays;

/*
--job.name=itemJob
 */
@Configuration
@RequiredArgsConstructor
public class ItemConfiguration {
//    ItemReader
//    ItemWriter
//    ItemProcessor

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemJob() {
        return this.jobBuilderFactory.get("itemJob")
                /* step start */
                .start(itemStep1())
                .next(itemStep2())
                .build();
    }

    @Bean
    public Step itemStep1() {
        return stepBuilderFactory.get("itemStep1")
                // SimpleChunkProvider (read())
                // -
                // SimpleChunkProcessor (doWrite(), doProcess())
                .<Customer, Customer>chunk(3)
                .reader(new CustomItemReader(Arrays.asList(
                        new Customer("user1"),
                        new Customer("user2"),
                        new Customer("user3"))))
                .processor(new CustomItemProcessor())
                .writer(new CustomItemWriter())
                .build();
    }

    @Bean
    public Step itemStep2() {
        return stepBuilderFactory.get("itemStep2")
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
