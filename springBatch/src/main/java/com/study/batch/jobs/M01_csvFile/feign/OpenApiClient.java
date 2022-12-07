package com.study.batch.jobs.M01_csvFile.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "vendor-api", url = "${feign.vendor-api.url}")
public interface OpenApiClient {
    /** 데이터 얻기 : http://apis.data.go.kr/1130000/MllBsStatsService/getMllBsStatsInfo */
    @GetMapping("")
    String getVendorApiData(@RequestParam String serviceKey,
                            @RequestParam Integer pageNo,
                            @RequestParam Integer numOfRows,
                            @RequestParam String resultType,
                            @RequestParam String fromYm,
                            @RequestParam String toYm);
}
