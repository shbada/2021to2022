package com.app.springbatch.batch.chunk.processor;

import com.app.springbatch.batch.domain.ApiRequestVO;
import com.app.springbatch.batch.domain.ProductVO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class ProcessorClassifier<C,T> implements Classifier<C, T> {

    private Map<String, ItemProcessor<ProductVO, ApiRequestVO>> processorMap = new HashMap<>();

    @Override
    public T classify(C classifiable) { // classifiable : ProductVO 객체가 들어온다.
        return (T) processorMap.get(((ProductVO)classifiable).getType());
    }

    public void setProcessorMap(Map<String, ItemProcessor<ProductVO, ApiRequestVO>> processorMap) {
        this.processorMap = processorMap;
    }
}
