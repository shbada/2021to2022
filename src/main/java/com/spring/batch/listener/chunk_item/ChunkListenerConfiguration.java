package com.spring.batch.listener.chunk_item;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ChunkListenerConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomChunkListener customChunkListener;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<Integer, String>chunk(10)
                .listener(customChunkListener)
                /**
                 * chunk size 순환 할때마다 수행
                 * 1) CustomChunkListener > before
                 * 아이템마다 수행
                 * 2) CustomItemReadListener > before
                 * 3) CustomItemReadListener > after
                 * 아이템마다 수행
                 * 4) CustomItemProcessListener > before
                 * 5) CustomItemProcessListener > after
                 * 리스트 처리 전 수행
                 * 6) CustomItemWriteListener > before
                 * 7) CustomItemWriteListener > after
                 * chunk size 순환 할때마다 수행
                 * 8) CustomChunkListener > after
                 */
                /* TaskletStep > beforeChunk(), afterChunk() */
                .listener(new CustomChunkListener()) // 하나의 chunk 주기 때마다 실행됨
                /* SimpleChunkProvider */
                .listener(new CustomItemReadListener())
                .listener(new CustomItemProcessListener())
                .listener(new CustomItemWriteListener())
                .reader(listItemReader())
                .processor((ItemProcessor) item -> {
                    throw new RuntimeException("failed");
//                    return "item" + item;
                })
                .writer((ItemWriter<String>) items -> {
                    throw new RuntimeException("failed");
                })
                .build();
    }

    @Bean
    public ItemReader<Integer> listItemReader() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        return new ListItemReader<>(list);
    }
}
