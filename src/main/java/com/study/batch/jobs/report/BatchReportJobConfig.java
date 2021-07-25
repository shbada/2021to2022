package com.study.batch.jobs.report;

import com.study.batch.common.OutputArea;
import com.study.batch.entity.BatchStepExecution;
import com.study.batch.entity.TempLibrary;
import com.study.batch.entity.TempLibraryLocal;
import com.study.batch.entity.TempLibraryType;
import com.study.batch.entity.dto.BatchStepExecutionDto;
import com.study.batch.entity.dto.TempLibraryDto;
import com.study.batch.repository.BatchStepExecutionRepository;
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
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchReportJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private static final String JOB_NAME = "batchReportJob";
    private static final int chunkSize = 100;

    /* jpa repository */
    private final BatchStepExecutionRepository batchStepExecutionRepository;

    /* redis writer */
    private final RedisWriter redisWriter;

    /**
     * Job
     * @return
     */
    @Bean(name = JOB_NAME)
    public Job batchReportJob() {
        return jobBuilderFactory.get("batchReportJob")
                .incrementer(new RunIdIncrementer())
                .start(batchReportStep())
                .build();
    }

    /**
     * Step
     * @return
     */
    @Bean(name = JOB_NAME +"_step")
    public Step batchReportStep() {
        return stepBuilderFactory.get("batchReportStep")
                .<BatchStepExecutionDto, BatchStepExecution>chunk(chunkSize)
                /* 배치 결과 테이블 읽어오기 */
                .reader(batchReportReaderStep())
                /* redis에 적재 */
                .writer(redisWriter)
                .build();
    }

    /**
     * Reader (BATCH_STEP_EXECUTION 테이블 읽어오기)
     * @return
     */
    @Bean(name = JOB_NAME +"_reader")
    public RepositoryItemReader<BatchStepExecutionDto> batchReportReaderStep() {
        RepositoryItemReader<BatchStepExecutionDto> reader = new RepositoryItemReader<>();
        reader.setRepository(batchStepExecutionRepository);
        reader.setMethodName("findAll");
        reader.setSort(Collections.singletonMap("stepExecutionId", Sort.Direction.DESC));
        return reader;
    }

}
