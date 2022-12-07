package com.study.batch.jobs.M02_vendor;

import com.study.batch.jobs.M01_csvFile.entity.TempLibrary;
import com.study.batch.jobs.M01_csvFile.entity.dto.TempLibraryDto;
import com.study.batch.jobs.M01_csvFile.steps.CsvFileItemTskletStep;
import com.study.batch.jobs.M02_vendor.steps.VendorItemReaderStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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

    private final VendorItemReaderStep vendorItemReaderStep;

    /**
     * Job
     * @return
     */
    @Bean(name = JOB_NAME)
    public Job vendorItemReaderJob() {
        return jobBuilderFactory.get("vendorItemReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(vendorItemTasklsetStep())
                .next(vendorItemReaderStep())
                .build();
    }

    /**
     * 공정위 데이토포털 API 호출
     * @return
     */
    @Bean(name = JOB_NAME +"_task_step")
    public Step vendorItemTasklsetStep(){
        return stepBuilderFactory.get("csvFileItemTasklsetStep")
                /* 엑셀 파일 읽고, static 변수에 저장 */
                .tasklet(new CsvFileItemTskletStep())
                .build();
    }

    /**
     * API Response
     * @return
     */
    @Bean(name = JOB_NAME +"_step")
    public Step vendorItemReaderStep() {
        return stepBuilderFactory.get("vendorItemReaderStep")
                .<String, String>chunk(chunkSize)
                .reader(vendorItemReaderStep.reader())
                //.processor(vendorItemReaderStep.processor())
                .writer(vendorItemReaderStep.writer() )
                .build();
    }
}
