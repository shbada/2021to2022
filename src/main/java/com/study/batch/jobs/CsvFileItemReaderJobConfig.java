package com.study.batch.jobs;

import com.study.batch.entity.TempLibrary;
import com.study.batch.entity.TempLibraryLocal;
import com.study.batch.entity.TempLibraryType;
import com.study.batch.entity.dto.TempLibraryDto;
import com.study.batch.steps.CsvFileItemTskletStep;
import com.study.batch.steps.LibraryItemReaderStep;
import com.study.batch.steps.LibraryLocalItemReaderStep;
import com.study.batch.steps.LibraryTypeItemReaderStep;
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
public class CsvFileItemReaderJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private static final String JOB_NAME = "csvFileItemReaderJob";
    private static final int chunkSize = 100;

    /* reader */
    private final LibraryLocalItemReaderStep libraryLocalItemReaderStep;
    private final LibraryTypeItemReaderStep libraryTypeItemReaderStep;
    private final LibraryItemReaderStep libraryItemReaderStep;

    /**
     * Job
     * @return
     */
    @Bean(name = JOB_NAME)
    public Job csvFileItemReaderJob() {
        return jobBuilderFactory.get("csvFileItemReaderJob")
                /* 1. 엑셀파일 Read 및 static 변수에 저장 */
                .incrementer(new RunIdIncrementer())
                .start(csvFileItemTasklsetStep())
                /* 2. static 변수 안의 데이터를 정규화된 Table에 저장 */
                .next(libraryItemReaderStep())
                .next(libraryLocalItemReaderStep())
                .next(libraryTypeItemReaderStep())
                .build();
    }

    /**
     * 엑셀 파일의 데이터를 static 변수에 저장하는 step
     * @return
     */
    @Bean(name = JOB_NAME +"_task_step")
    public Step csvFileItemTasklsetStep(){
        return stepBuilderFactory.get("csvFileItemTasklsetStep")
                /* 엑셀 파일 읽고, static 변수에 저장 */
                .tasklet(new CsvFileItemTskletStep())
                .build();
    }

    /**
     * TEMP_LIBRARY 테이블 저장 step
     * @return
     */
    @Bean(name = JOB_NAME +"_step")
    public Step libraryItemReaderStep() {
        return stepBuilderFactory.get("libraryItemReaderStep")
                .<TempLibraryDto, TempLibrary>chunk(chunkSize)
                .reader(libraryItemReaderStep.csvFileItemReader())
                .processor(libraryItemReaderStep.processor())
                .writer(libraryItemReaderStep.writer() )
                .build();
    }

    /**
     * TEMP_LIBRARY_LOCAL 테이블 저장 step
     * @return
     */
    @Bean(name = JOB_NAME +"_local_step")
    public Step libraryLocalItemReaderStep() {
        return stepBuilderFactory.get("libraryLocalItemReaderStep")
                .<TempLibraryDto, TempLibraryLocal>chunk(chunkSize)
                .reader(libraryLocalItemReaderStep.csvFileItemReader())
                .processor(libraryLocalItemReaderStep.processor())
                .writer(libraryLocalItemReaderStep.writer() )
                .build();
    }

    /**
     * TEMP_LIBRARY_TYPE 테이블 저장 step
     * @return
     */
    @Bean(name = JOB_NAME +"_type_step")
    public Step libraryTypeItemReaderStep() {
        return stepBuilderFactory.get("libraryTypeItemReaderStep")
                .<TempLibraryDto, TempLibraryType>chunk(chunkSize)
                .reader(libraryTypeItemReaderStep.csvFileItemReader())
                .processor(libraryTypeItemReaderStep.processor())
                .writer(libraryTypeItemReaderStep.writer() )
                .build();
    }
}
