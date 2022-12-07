package com.spring.batch._65_processor_classifer;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class ProcessorClassifier<C, T> implements Classifier<C, T> {

    private Map<Integer, ItemProcessor<ProcessorInfo, ProcessorInfo>> processorMap = new HashMap<>();

    /**
     * 어떤 ItemProcessor 을 선택할지의 로직 구현
     * @param classifiable
     * @return
     */
    @Override
    public T classify(C classifiable) {
        // C : ProcessInfo
        // T : ItemProcessor (processorMap 의 value)
        // getId() : ProcessorInfo 의 getId() (ItemReader 에서 담음)
        return (T) processorMap.get(((ProcessorInfo) classifiable).getId());
    }

    /**
     * Integer : key 1 에 해당하는 ItemProcessor , key 2 에 해당하는 ItemProcessor ...
     * @param processorMap
     */
    public void setProcessorMap(Map<Integer, ItemProcessor<ProcessorInfo, ProcessorInfo>> processorMap) {
        this.processorMap = processorMap;
    }
}