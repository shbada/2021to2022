package com.elk.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "open-api", url = "${feign.open-api.url}")
public interface OpenApiClient {
    final String key = "416150414d73656f39334a75425247";

    /** 데이터 얻기 : https://data.seoul.go.kr/dataList/OA-2271/A/1/datasetView.do */
    @GetMapping(key + "/json/ListPublicReservationEducation/{START_INDEX}/{END_INDEX}/")
    String getOpenApiData(@PathVariable Integer START_INDEX, @PathVariable Integer END_INDEX);
}
