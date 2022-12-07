package com.example.async.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@EnableAsync
public class RestTemplateService {
    private static final AtomicInteger counter = new AtomicInteger(0);

    @Async(value = "myThreadPool")
    public ListenableFuture<String> work(String req) {
        return new AsyncResult<>(req + "/asyncwork");
    }

    public void loadTest(String url) throws InterruptedException {
        basic(url);
    }

    /**
     * 100개의 스레드 생성하여 호출
     * @param url
     * @throws InterruptedException
     */
    private static void basic(String url) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(100);

        RestTemplate rt = new RestTemplate();

        StopWatch main = new StopWatch();
        main.start();

        /*
            스레드풀을 100개를 만들어놓고, 100번 루프를 돌면서 스레드를 하나하나 만드는 것
            빠른 속도로 쓰레드가 생성되서 동시에 되는것처럼 보이지만, 이는 순차적으로 100개의 스레드가 만들어지면서
            요청을 만드는 코드가 순차적으로 수행되는 것이다.
            100개의 스레드를 전부 생성하고 동시에 만약 100개를 딱 실행시키고 싶다면?
            -> 이는 스레드의 동기화 기법 (CyclicBarrier)
         */
        CyclicBarrier barrier = new CyclicBarrier(101);

        for (int i = 0; i < 100; i++) {
            es.submit(() -> {
                int idx = counter.addAndGet(1);

                // 이 코드를 만난 순간, 100번째 스레드를 생성해서 여기에 오면 블로킹되었던 100개가 한꺼번에 아래 코드에 진입된다.
                barrier.await();

                // 에러처리가 람다식 안에 있으면 문제다. Runnable 은 기본적으로 Exception을 던지도록 정의되어있지 않다.
                // 밖으로 던질 방법은 없고 대신 try~catch로 묶어야한다.
                // 이 방법 대신 Callable을 구현하도록 하자. (es.submit()으로 변경) -> Excpetion을 던지도록 되어있다.
                log.info("Thread: {}", idx);

                StopWatch sw = new StopWatch();
                sw.start();

                String res = rt.getForObject(url, String.class, idx);

                sw.stop();
                log.info("Elapsed: {} {} / {}", idx, sw.getTotalTimeMillis(), res);

                return null; // 이 한줄이 추가되면, Callable 로 컴파일러가 유추하게된다.
            });
        }

        // 101개 - 여기에 도달하는 순간에 전체를 보낸다.!
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        es.shutdown();
        es.awaitTermination(100, TimeUnit.SECONDS); // 대기작업이 끝날때까지 100초까지만 기다린다.
        main.stop();

        log.info("Total : {}", main.getTotalTimeSeconds());
    }
}
