package com.study.batch.steps;

import com.study.batch.common.OutputArea;
import com.study.batch.entity.TempLibrary;
import com.study.batch.entity.TempLibraryLocal;
import com.study.batch.entity.dto.TempLibraryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LibraryItemReaderStep {
    private final EntityManagerFactory entityManagerFactory;

    private static final String JOB_NAME = "LibraryItemReaderJob";

    /**
     * Reader
     * @return
     */
    @Bean(name = JOB_NAME +"_reader")
    public FlatFileItemReader<TempLibraryDto> csvFileItemReader() {
        return OutputArea.outputList = new FlatFileItemReaderBuilder<TempLibraryDto>()
                .name("csvFileItemReader")
                .resource(new ClassPathResource("/temp.csv"))
                .linesToSkip(1) // header line skip
                .encoding("MS949")
                .lineTokenizer(new DelimitedLineTokenizer() {{
                    setNames("libraryNm", "bigLocal", "smallLocal", "libraryType");
                    setStrict(false);
                }})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<TempLibraryDto>() {{
                    setTargetType(TempLibraryDto.class);
                }})
                .build();
    }

    /**
     * Processor
     * @return
     */
    @StepScope
    @Bean(name = JOB_NAME +"_processor")
    public ItemProcessor<TempLibraryDto, TempLibrary> processor() {
        /* JPA는 entity에 set을 하면 안된다. 빌더패턴을 적용하자. */
        return TempLibraryDto::toEntity;
    }

    /**
     * Writer
     * @return
     */
    @Bean(name = JOB_NAME +"_writer")
    @StepScope
    public JpaItemWriter<TempLibrary> writer() {
        return new JpaItemWriterBuilder<TempLibrary>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
