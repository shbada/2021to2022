package com.app.springbatch.batch.job.apiJob;

import com.app.springbatch.batch.listener.JobListener;
import com.app.springbatch.batch.tasklet.ApiEndTasklet;
import com.app.springbatch.batch.tasklet.ApiStartTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=apiJob
 */
@Configuration
@RequiredArgsConstructor
public class ApiConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final ApiStartTasklet apiStartTasklet;
    private final ApiEndTasklet apiEndTasklet;

    private final Step jobStep;


    @Bean
    public Job apiJob() {
        return jobBuilderFactory.get("apiStep")
                .listener(new JobListener())
                .start(apiStep1())
                .next(jobStep)
                .next(apiStep2())
                .build();
    }

    @Bean
    public Step apiStep1() {
        return stepBuilderFactory.get("apiStep1")
                .tasklet(apiStartTasklet)
                .build();
    }

    @Bean
    public Step apiStep2() {
        return stepBuilderFactory.get("apiStep2")
                .tasklet(apiEndTasklet)
                .build();
    }
}
