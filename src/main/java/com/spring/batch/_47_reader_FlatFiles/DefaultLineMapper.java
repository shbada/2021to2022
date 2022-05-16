package com.spring.batch._47_reader_FlatFiles;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;

public class DefaultLineMapper<T> implements LineMapper<T> {

    private LineTokenizer tokenizer;
    private FieldSetMapper<T> fieldSetMapper;


    /**
     * line 하나, lineNumber 인자로 받는다.
     * @param line
     * @param lineNumber
     * @return
     * @throws Exception
     */
    public T mapLine(String line, int lineNumber) throws Exception {
        FieldSet fieldSet = tokenizer.tokenize(line); // tokenizer : DelimitedLineTokenizer (FlatFilesConfiguration 에서 설정)
        return fieldSetMapper.mapFieldSet(fieldSet);
    }

    public void setLineTokenizer(LineTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void setFieldSetMapper(FieldSetMapper<T> fieldSetMapper) {
        this.fieldSetMapper = fieldSetMapper;
    }

}
