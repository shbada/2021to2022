 package com.spring.batch._41_itemStream;

import com.spring.batch._41_itemStream.custom.CustomItemStreamReader;
import com.spring.batch._41_itemStream.custom.CustomItemStreamWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/*
--job.name=itemStreamJob
 */
@Configuration
@RequiredArgsConstructor
public class ItemStreamConfiguration {
    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemStreamJob() {
        return this.jobBuilderFactory.get("itemStreamJob")
                /* step start */
                .start(itemStreamStep1())
                .next(itemStreamStep2())
                .build();
    }

    @Bean
    public Step itemStreamStep1() {
        return stepBuilderFactory.get("itemStreamStep1")
                // AbstractStep - open()
                // TaskletStep - open()
                // CompositeItemStream > open() (여기서 streams에 우리가 만든 Reader, Writer 이 담겨져있다)

                // ItemReader 의 open() -> ItemWriter 의 open()

                // chunk 프로세스 전에 한번 update 되긴함 (reader update())
                // chunk Reader 프로세스 수행
                // ItemReader 의 update()

                // chunk Writer 프로세스 수행
                // ItemWriter 의 update()

                // 모든 수행이 끝나면 close()

                .<String, String>chunk(5)
                .reader(customItemStreamReader())
                .writer(customItemStreamWriter())
                .build();
    }

    /**
     * item stream writer
     * @return
     */
    private ItemWriter<? super String> customItemStreamWriter() {
        return new CustomItemStreamWriter();
    }

    /**
     * item stream reader
     * @return
     */
    private ItemReader<String> customItemStreamReader() {
        List<String> items = new ArrayList<>(10);

        for (int i = 0; i <= 10; i++) {
            items.add(String.valueOf(i));
        }

        return new CustomItemStreamReader(items);
    }

    @Bean
    public Step itemStreamStep2() {
        return stepBuilderFactory.get("itemStreamStep2")
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
