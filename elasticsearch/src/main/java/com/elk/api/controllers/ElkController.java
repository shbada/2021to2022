package com.elk.api.controllers;

import com.elk.api.common.CommonResponse;
import com.elk.api.service.ElkService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"ElkController"})
@RestController
@RequestMapping("/elk")
@RequiredArgsConstructor
@Slf4j
public class ElkController {
    private final CommonResponse commonResponse;
    private final ElkService elkService;

    /**
     * create Index
     * @return
     */
    @PostMapping("/reservation/index")
    public ResponseEntity<?> createIndex() throws Exception {
        /* create Index */
        return commonResponse.send(elkService.createIndex());
    }

    /**
     * select Index
     * @return
     */
    @GetMapping("/reservation/index")
    public ResponseEntity<?> selectIndex() throws Exception {
        /* create Index */
        return commonResponse.send(elkService.selectIndex());
    }
}
