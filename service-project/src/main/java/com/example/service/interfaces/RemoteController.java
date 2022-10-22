package com.example.service.interfaces;

import com.example.service.application.RemoteFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RemoteController {
    private final RemoteFacade remoteFacade;

    /**
     * server.tomcat.max-threads=1
     * 서버 로그를 확인하면 nio-8080-exec-1 라는 이름을 가진 1개의 스레드가 요청을 처리
     * @return
     * @throws Exception
     */
    @GetMapping("/callable")
    public Callable<String> callable() throws Exception {
        log.info("callable");
        return () -> {
            log.info("async");
            Thread.sleep(2000);
            return "hello";
        };
    }

    /**
     * server.tomcat.max-threads=1
     * nio-8080-exec-1 라는 이름을 가진 한개의 Tomcat 스레드 + 'worker-' 이름을 가진 100개의 워커 스레드
     * @return
     * @throws Exception
     */
    @GetMapping("/callable2")
    public Callable<String> callable2() throws Exception {
        return remoteFacade.callable();
    }

//    public String callable() throws Exception {
//        // [r-http-kqueue-2] c.e.async.interfaces.TestController      : callable
//        // [r-http-kqueue-2] c.e.async.interfaces.TestController      : async
//        log.info("callable");
//
//        Callable<String> async = () -> {
//            log.info("async");
//            Thread.sleep(2000);
//            return "hello";
//        };
//
//        return async.call();
//    }

    @GetMapping("/service")
    public String service(String req) throws InterruptedException {
        Thread.sleep(2000);
        return req + "/service";
    }

    @GetMapping("/service2")
    public String service2(String req) throws InterruptedException {
        return req + "/service2";
    }
}
