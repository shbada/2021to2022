package com.spring.batch._66_processor_delegate;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor1 implements ItemProcessor<String, String> {

    int cnt = 0;

    @Override
    public String process(String item) throws Exception {
        cnt++;
        return item + cnt;
    }
}
