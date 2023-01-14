package com.spring.batch._65_processor_classifer;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor2 implements ItemProcessor<ProcessorInfo, ProcessorInfo> {
    @Override
    public ProcessorInfo process(ProcessorInfo item) throws Exception {

        System.out.println("CustomItemProcessor2");

        return item;
    }
}
