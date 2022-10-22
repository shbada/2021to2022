package com.example.async.interfaces;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 지연된 결과”를 의미하며 외부의 이벤트 혹은 클라이언트 요청에 의해서 지연되어 있는 HTTP 요청에 대한 응답을 나중에 써줄 수 있는 기술
 * 별도로 워커 스레드를 만들어 대기하지 않고도 처리가 가능
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/deferred")
public class DeferredResultController {
    Queue<DeferredResult<String>> results = new ConcurrentLinkedQueue<>();

    @GetMapping("/dr")
    public DeferredResult<String> dr() {
        log.info("dr");
        DeferredResult<String> dr = new DeferredResult<>();
        results.add(dr);
        return dr;
    }

    @GetMapping("/dr/count")
    public String drCount() {
        return String.valueOf(results.size());
    }

    @GetMapping("/dr/event")
    public String drEvent(String msg) {
        results.forEach(dr -> {
            dr.setResult("Hello " + msg);
            results.remove(dr);
        });

        return "OK";
    }
}
