package com.study.batch.jobs.M02_vendor.steps;

import com.study.batch.jobs.M01_csvFile.feign.OpenApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class VendorItemReaderStep {
    private final EntityManagerFactory entityManagerFactory;
    private final OpenApiClient openApiClient;

    final String key = "oxYGSkitZD24s67p2kNyKZK0GuEIvMPfwRlBMVIV0oJ5RGmhVi3c%2BfbFf0L8fvbw7%2Bn3xgAqEiLnL23ugXbmvA%3D%3D";
    private static final String JOB_NAME = "VendorItemReaderJob";

    /**
     * Reader
     * @return
     */
    @Bean(name = JOB_NAME +"_reader")
    public ItemReader<String> reader() {
        String response = openApiClient.getVendorApiData(key, 1, 10,
                                                "json", "201002", "202103");

        return new ItemReader<String>() {
            @Override
            public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                return response;
            }
        };
    }

    /**
     * Writer
     * @return
     */
    @Bean(name = JOB_NAME +"_writer")
    @StepScope
    public ItemWriter<String> writer() {
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) throws Exception {
                list.forEach(System.out::println);
            }
        };
    }
}
