package com.example.async.interfaces;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.concurrent.Executors;

/**
 * ResponseBodyEmitter
 * 비동기 요청 처리의 결과로 하나 이상의 응답을 위해 사용되는 리턴 값 Type
 * 여러개의 결과를 만들어 요청을 처리
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/emitter")
public class EmitterController {
    @GetMapping("/emitter")
    public ResponseBodyEmitter emitter() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                for (int i = 0; i < 50; i++) {
                    emitter.send("<p>Stream " + i + "</p>");
                    Thread.sleep(100);
                }
            } catch (Exception e) {

            }
        });

        return emitter;
    }
}
