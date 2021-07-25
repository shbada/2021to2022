package com.example.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "elasticsearch-api", url = "${feign.elasticsearch-api.url}")
public interface ElasticSearchClient {
    /** test 인덱스 조회 */
    @GetMapping("/reservation/_search?pretty")
    String selectIndex();

    /** test 리스트 조회 */
    @PostMapping(value = "/reservation/_search", consumes = "application/json")
    String getTestList(@RequestBody String param);
}
