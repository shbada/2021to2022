package com.example.async.domain;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class TestService {
    /**
     * 해당 메서드를 비동기적으로 호출
     * 해당 메서드를 호출한 호출자(caller)는 즉시 리턴하고, 메소드의 실제 실행은 Spring TaskExecutor에 의해서 실행
     *
     * @Async(value = "myThreadPool") 와 같이 value를 명시하지 않으면, default : SimpleAsyncTaskExecutor
     * SimpleAsyncTaskExecutor는 각 비동기 호출마다 계속 새로운 스레드를 만들어 사용하기 때문에 비효율적
     * Controller 메서드를 비동기로 변경해도 해당 처리가 서블릿 스레드가 아닌,
     * 다른 스레드에서 발생한다는 점을 제외하면 기존 Controller 메서드의 동작 방식과는 큰 차이가 없다.
     * @param req
     * @return
     */
    @Async
    public CompletableFuture<String> work(String req) {
        return CompletableFuture.completedFuture(req + "/asyncwork");
    }
}
