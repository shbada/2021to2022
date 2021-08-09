package com.study.batch.common;

import com.study.batch.jobs.M01_csvFile.entity.dto.TempLibraryDto;
import org.springframework.batch.item.file.FlatFileItemReader;

public class OutputArea {
    public static FlatFileItemReader<TempLibraryDto> outputList = new FlatFileItemReader<>();
}
