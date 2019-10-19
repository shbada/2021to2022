package com.test.technology.controllers;

import com.test.technology.commons.Output;
import com.test.technology.feign.TestClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = {"FeignController"})
@RestController
@RequestMapping("/feign")
@Slf4j
public class FeignController {

    private final Output output;
    private final TestClient testClient;

    public FeignController(Output output, TestClient testClient) {
        this.output = output;
        this.testClient = testClient;
    }

    @GetMapping("")
    public ResponseEntity<?> getFeignConnect(HttpServletRequest request) {
        String result = testClient.authTest();
        log.info("결과 ::: " + result);
        return output.send(result);
    }

    /**
     * 외부 API의 Method 명과 상관이 있을까?
     * @param request
     * @return
     */
    @ApiOperation(value = "case 1. 호출되는 API method 명을 다르게할때")
    @GetMapping("/test")
    public ResponseEntity<?> getFeignConnect1(HttpServletRequest request) {
        String result = testClient.paramTest("seohae");
        log.info("결과 ::: " + result);
        return output.send(result);
    }

    /**
     * @RequestParam 어노테이션의 name을 꼭 써야할까?
     * @param request
     * @return
     */
    @ApiOperation(value = "case 2. @RequestParam 안의 name 을 안썻을때")
    @GetMapping("/test2")
    public ResponseEntity<?> getFeignConnect2(HttpServletRequest request) {
        String result = testClient.paramTest2("seohae");
        log.info("결과 ::: " + result);
        return output.send(result);
    }

    /**
     * @RequestParam 어노테이션의 name을 안썼을때 파라미터 변수명과 상관이 있을까?
     * 500 Error 발생!
     * @param request
     * @return
     */
    @ApiOperation(value = "case 4. 파라미터 변수명을 다르게했을때 name 속성을 안썼을때")
    @GetMapping("/test4")
    public ResponseEntity<?> getFeignConnect4(HttpServletRequest request) {
        String result = testClient.paramTest4("seohae");
        log.info("결과 ::: " + result);
        return output.send(result);
    }

    /**
     * 외부 API의 파라미터 변수명과 상관이 있을까?
     * @param request
     * @return
     */
    @ApiOperation(value = "case 3. 변수명을 다르게하고 @RequestParam 안의 name을 넣었을때")
    @GetMapping("/test3")
    public ResponseEntity<?> getFeignConnect3(HttpServletRequest request) {
        String result = testClient.paramTest3("seohae");
        log.info("결과 ::: " + result);
        return output.send(result);
    }
}
