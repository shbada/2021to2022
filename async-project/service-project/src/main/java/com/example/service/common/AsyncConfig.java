package com.example.service.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {
    @Bean
    ThreadPoolTaskExecutor myThreadPool() {
        ThreadPoolTaskExecutor te = new ThreadPoolTaskExecutor();
        // 어떤 경우에도 스레드를 1개만 갖겠다는 설정
        // core 가 다 차면, Queue 를 채우고, Queue 까지 다 찼을때 maxPoolSize 까지 스레드 개수를 추가하다가 그때도 부족하면 에러가 난다.

        // 1) 스레드 풀을 해당 개수까지 기본적으로 생성함. 처음 요청이 들어올 때 poll size만큼 생성한다.
        te.setCorePoolSize(100);
        // 2) 지금 당장은 Core 스레드를 모두 사용중일때, 큐에 만들어 대기시킨다.
        te.setQueueCapacity(50);
        // 3) 대기하는 작업이 큐에 꽉 찰 경우, 풀을 해당 개수까지 더 생성한다.
        te.setMaxPoolSize(100);

        te.setThreadNamePrefix("worker-");
        te.initialize();

        return te;
    }
}
