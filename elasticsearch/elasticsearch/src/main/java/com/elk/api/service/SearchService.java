package com.elk.api.service;

import com.elk.api.common.CommonUtils;
import com.elk.api.feign.ElasticSearchClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {
    private final ElasticSearchClient elasticSearchClient;

    public String getReservationList() throws IOException {
        String param = CommonUtils.getFileJsonParam("getReservationList.json");
        return elasticSearchClient.getReservationList(param);
    }
}
