package com.study.batch.jobs.report;

import com.study.batch.entity.BatchStepExecution;
import com.study.batch.entity.dto.BatchStepExecutionDto;
import com.study.batch.repository.BatchStepExecutionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Collections;

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
