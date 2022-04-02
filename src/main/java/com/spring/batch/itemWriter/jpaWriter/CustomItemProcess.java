package com.spring.batch.itemWriter.jpaWriter;

import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcess implements ItemProcessor<CustomerForm, CustomerEntity> {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CustomerEntity process(CustomerForm item) throws Exception {
        return modelMapper.map(item, CustomerEntity.class);
    }
}
