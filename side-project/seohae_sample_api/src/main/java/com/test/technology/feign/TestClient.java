package com.test.technology.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "test-api", url = "${feign.test-api.url}")
public interface TestClient {

    @GetMapping("/test")
    String authTest();

    /** 외부 API의 Method 명과 상관이 있을까? */
    @GetMapping("/test")
    String authTest2();

    @GetMapping("/test/user")
    String paramTest(@RequestParam String userID);

    /** @RequestParam 어노테이션의 name을 꼭 써야할까? */
    @GetMapping("/test/user")
    String paramTest2(@RequestParam(name = "userID") String userID);

    /** @RequestParam 어노테이션의 name을 안썼을때 파라미터 변수명과 상관이 있을까? */
    @GetMapping("/test/user")
    String paramTest4(@RequestParam String user);

    /** 외부 API의 파라미터 변수명과 상관이 있을까? */
    @GetMapping("/test/user")
    String paramTest3(@RequestParam(name = "userID") String user);
}
