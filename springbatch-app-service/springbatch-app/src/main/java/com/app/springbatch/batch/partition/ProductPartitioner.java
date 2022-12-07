package com.app.springbatch.batch.partition;

import com.app.springbatch.batch.job.apiJob.QueryGenerator;
import com.app.springbatch.batch.domain.ProductVO;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ProductPartitioner implements Partitioner {
    private DataSource dataSource;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        // type 1, 2, 3
        ProductVO[] productList = QueryGenerator.getProductList(dataSource);
        Map<String, ExecutionContext> result = new HashMap<>();
        int number = 0;

        for (ProductVO productVO : productList) { // type 만 들어있음
            ExecutionContext value = new ExecutionContext();

            result.put("partition" + number, value);

            // 실제 executionContext 에는 우리가 ItemReader, ItemProcessor, ItemWriter 에서
            // 우리가 표현식의 값으로 StepScope 사용해서 값을 바인딩 할 수 있게 하기위해서 셋팅
            value.put("product", productVO);

            number++;
        }

        return result;
    }
}
