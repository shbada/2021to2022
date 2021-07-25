package com.study.batch.steps;

import com.study.batch.common.OutputArea;
import com.study.batch.entity.dto.TempLibraryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvFileItemTskletStep implements Tasklet {
    /**
     * excel file reader + writer(set static field)
     * @param stepContribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        /* static 변수에 저장 */
        OutputArea.outputList = new FlatFileItemReaderBuilder<TempLibraryDto>()
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
        return RepeatStatus.FINISHED;
    }
}
