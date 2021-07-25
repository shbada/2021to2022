package com.elk.api.controllers;

import com.elk.api.common.CommonResponse;
import com.elk.api.service.SearchService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Elasticsearch search query study
 */
@Api(tags = {"SearchController"})
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@Slf4j
public class SearchController {
    private final CommonResponse commonResponse;
    private final SearchService searchService;

    /**
     * 리스트 조회
     * @return
     * @throws Exception
     */
    @GetMapping("/getReservationList")
    public ResponseEntity<?> getReservationList() throws Exception {
        return commonResponse.send(searchService.getReservationList());
    }

}
