package com.sample.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "batch-api", url = "${feign.batch-api.url}")
public interface BatchClient {
    /** csv file read job */
    @GetMapping("/batch/csvFileItemReaderJob")
    String csvFileItemReaderJob();

    /** batch report job */
    @GetMapping("/batch/batchReportJob")
    String batchReportJob();
}
