package com.study.batch.steps;

import com.study.batch.common.OutputArea;
import com.study.batch.entity.TempLibraryType;
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
public class LibraryTypeItemReaderStep {
    private final EntityManagerFactory entityManagerFactory;

    private static final String JOB_NAME = "LibraryTypeItemReaderJob";

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
    public ItemProcessor<TempLibraryDto, TempLibraryType> processor() {
        /* JPA는 entity에 set을 하면 안된다. 빌더패턴을 적용하자. */
        return TempLibraryDto::toTypeEntity;
    }

    /**
     * Writer
     * @return
     */
    @Bean(name = JOB_NAME +"_writer")
    @StepScope
    public JpaItemWriter<TempLibraryType> writer() {
        return new JpaItemWriterBuilder<TempLibraryType>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
