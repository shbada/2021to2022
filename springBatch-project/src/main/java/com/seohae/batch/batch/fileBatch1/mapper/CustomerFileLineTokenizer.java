package com.seohae.batch.batch.fileBatch1.mapper;

import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FieldSetFactory;
import org.springframework.batch.item.file.transform.LineTokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * flatFileItemReaderStep4 의 .lineTokenizer(new CustomerFileLineTokenizer()) 설정 추가
 */
public class CustomerFileLineTokenizer implements LineTokenizer {
    private String delimiter = ",";
    private String[] names = new String[] {
            "firstName", "middleInitial", "lastName",
            "addressNumber", "street", "city", "state", "zipCode"};

    private FieldSetFactory fieldSetFactory = new DefaultFieldSetFactory();

    /**
     * 각 레코드를 전달받는 메소드를 통해 구현
     * @param record
     * @return
     */
    @Override
    public FieldSet tokenize(String record) {
        /* 구분자로 레코드 가져오기 */
        String[] fields = record.split(delimiter);

        List<String> parseFields = new ArrayList<>();

        for (int i = 0; i < fields.length; i++) {
            if (i == 4) {
                parseFields.set(i - 1, parseFields.get(i - 1) + " " + fields[i]);
            } else {
                parseFields.add(fields[i]);
            }
        }

        return fieldSetFactory.create(parseFields.toArray(new String[0]), names);
    }
}
