package com.app.springbatch.batch.chunk.processor;

import com.app.springbatch.batch.domain.Product;
import com.app.springbatch.batch.domain.ProductVO;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;

public class FileItemProcessor implements ItemProcessor<ProductVO, Product> {

    @Override
    public Product process(ProductVO item) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        // item -> Product
        return modelMapper.map(item, Product.class);
    }
}
