package com.study.batch.jobs.M02_vendor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class VendorItemReaderJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private static final String JOB_NAME = "vendorItemReaderJob";
    private static final int chunkSize = 100;


    /**
     * Job
     * @return
     */
    @Bean(name = JOB_NAME)
    public Job vendorItemReaderJob() {
//        return jobBuilderFactory.get("vendorItemReaderJob")
//                .incrementer(new RunIdIncrementer())
//                .start(test())
//                .next(test())
//                .build();
        return null;
    }
}
