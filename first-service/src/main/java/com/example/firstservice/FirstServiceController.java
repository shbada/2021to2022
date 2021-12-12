package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service") /* api gateway yml 참고 */
@Slf4j
public class FirstServiceController {

    Environment env;

    public FirstServiceController(Environment env) {
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);

        /* Response header 는 개발자도구로 확인 */
        return "Hello World In First Service Header message";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server Port = {}", request.getServerPort());
        return String.format("Hi, there. This is a message from First Service on PORT %s"
                , env.getProperty("local.server.port"));  // 랜덤 포트 가져오기
    }
}
