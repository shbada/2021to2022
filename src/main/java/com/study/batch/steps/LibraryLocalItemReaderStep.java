package com.study.batch.steps;

import com.study.batch.common.OutputArea;
import com.study.batch.entity.TempLibraryLocal;
import com.study.batch.entity.dto.TempLibraryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LibraryLocalItemReaderStep {
    private final EntityManagerFactory entityManagerFactory;

    private static final String JOB_NAME = "LibraryLocalItemReaderJob";

    /**
     * Reader
     * @return
     */
    @Bean(name = JOB_NAME +"_reader")
    public FlatFileItemReader<TempLibraryDto> csvFileItemReader() {
        return OutputArea.outputList;
    }

    /**
     * Processor
     * @return
     */
    @StepScope
    @Bean(name = JOB_NAME +"_processor")
    public ItemProcessor<TempLibraryDto, TempLibraryLocal> processor() {
        /* JPA는 entity에 set을 하면 안된다. 빌더패턴을 적용하자. */
        return TempLibraryDto::toLocalEntity;
    }

    /**
     * Writer
     * @return
     */
    @Bean(name = JOB_NAME +"_writer")
    @StepScope
    public JpaItemWriter<TempLibraryLocal> writer() {
        return new JpaItemWriterBuilder<TempLibraryLocal>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
