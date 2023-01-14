package com.project.springbatch._66_processor_delegate;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/*
--job.name=CompositionItemJob
*/
@RequiredArgsConstructor
@Configuration
public class CompositionItemConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job CompositionItemJob() throws Exception {
        return jobBuilderFactory.get("CompositionItemJob")
                .incrementer(new RunIdIncrementer())
                .start(CompositionItemStep1())
                .build();
    }

    @Bean
    public Step CompositionItemStep1() throws Exception {
        return stepBuilderFactory.get("CompositionItemStep1")
                .<String, String>chunk(10)
                .reader(new ItemReader<String>() {
                    int i = 0;
                    @Override
                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                        i++;
                        return i > 10 ? null : "item";
                    }
                })
                .processor(compositionItemProcessor())
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        System.out.println(items);
                    }
                })
                .build();
    }

    @Bean
    public CompositeItemProcessor compositionItemProcessor() {
        /*
            ItemProcessor 체이닝 수행
         */
        CompositeItemProcessor<String,String> compositeProcessor = new CompositeItemProcessor<>();
        List itemProcessors = new ArrayList();
        itemProcessors.add(new CustomItemProcessor1());
        itemProcessors.add(new CustomItemProcessor2());

        return new CompositeItemProcessorBuilder<>()
                .delegates(itemProcessors)
                .build();
    }
}
