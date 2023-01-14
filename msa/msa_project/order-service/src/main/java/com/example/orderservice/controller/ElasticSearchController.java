package com.example.orderservice.controller;

import com.example.orderservice.common.CommonResponse;
import com.example.orderservice.service.ElasticSearchService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"ElasticSearchController"})
@RestController
@RequestMapping("/elasticsearch")
@RequiredArgsConstructor
@Slf4j
public class ElasticSearchController {
    private final CommonResponse commonResponse;
    private final ElasticSearchService elasticSearchService;

    /**
     * create Index
     *
     * @return
     */
    @PostMapping("/reservation/index")
    public ResponseEntity<?> createIndex() throws Exception {
        /* create Index */
        return commonResponse.send(elasticSearchService.createIndex());
    }

    /**
     * select Index
     *
     * @return
     */
    @GetMapping("/reservation/index")
    public ResponseEntity<?> selectIndex() throws Exception {
        /* create Index */
        return commonResponse.send(elasticSearchService.selectIndex());
    }
}

