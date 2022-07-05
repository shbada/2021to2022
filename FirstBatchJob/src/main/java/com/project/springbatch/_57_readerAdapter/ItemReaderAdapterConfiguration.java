package com.project.springbatch._57_readerAdapter;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=itemReaderAdapterJob
 */
@RequiredArgsConstructor
@Configuration
public class ItemReaderAdapterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemReaderAdapterJob() throws Exception {
        return jobBuilderFactory.get("itemReaderAdapterJob")
                .incrementer(new RunIdIncrementer())
                .start(itemReaderAdapterStep1())
                .build();
    }

    @Bean
    public Step itemReaderAdapterStep1() throws Exception {
        return stepBuilderFactory.get("itemReaderAdapterStep1")
                .<String,String>chunk(10)
                .reader(itemReaderAdapterCustomItemReader())
                .writer(itemReaderAdapterCustomItemWriter())
                .build();
    }

    @Bean
    public ItemReaderAdapter itemReaderAdapterCustomItemReader() {
        ItemReaderAdapter reader = new ItemReaderAdapter();
        reader.setTargetObject(customService());
        reader.setTargetMethod("joinCustomer");

        return reader;
    }

    private CustomService<String> customService() {
        return new CustomService<>();
    }

    @Bean
    public ItemWriter<String> itemReaderAdapterCustomItemWriter() {
        return items -> {
            System.out.println(items);
        };
    }
}

