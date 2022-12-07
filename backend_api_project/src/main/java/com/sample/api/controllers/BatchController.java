package com.sample.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.api.commons.Output;
import com.sample.api.feign.BatchClient;
import com.sample.api.service.BatchService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"BatchController"})
@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
@Slf4j
public class BatchController {
    private final Output output;
    private final BatchClient batchClient;
    private final BatchService batchService;

    /**
     * csv file read job call
     * @return
     */
    @PostMapping("/csvFileItemReaderJob")
    public ResponseEntity<?> csvFileItemReaderJob() {
        /* batch call */
        return output.send(batchClient.csvFileItemReaderJob());
    }

    /**
     * batch report job call
     * @return
     */
    @PostMapping("/batchReportJob")
    public ResponseEntity<?> batchReportJob() {
        /* batch call */
        return output.send(batchClient.batchReportJob());
    }

    /**
     * BATCH 결과 API
     * @return
     */
    @GetMapping("/getBatchReport")
    public ResponseEntity<?> getBatchReport() throws JsonProcessingException {
        /* service call */
        return output.send(batchService.getBatchReport());
    }
}
