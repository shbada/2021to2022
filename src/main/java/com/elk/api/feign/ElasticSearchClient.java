package com.elk.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "elk-api", url = "${feign.elk-api.url}")
public interface ElasticSearchClient {
    /* reserrvation 인덱스 조회 */
    @GetMapping("/reservation/_search?pretty")
    String selectIndex();

    /** reservation 리스트 조회 */
    @PostMapping(value = "/reservation/_search", consumes = "application/json")
    String getReservationList(@RequestBody String param);
}
