package com.example.async.interfaces;

import com.example.async.application.RestTemplateFacade;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/async")
public class AsyncRestTemplateController {
    private final RestTemplateFacade restTemplateFacade;

    static final String SERVICE_URL1 = "http://localhost:8081/service?req={req}";
    static final String SERVICE_URL2 = "http://localhost:8081/service2?req={req}";

    // 논블로킹 방식
    AsyncRestTemplate rt = new AsyncRestTemplate(
            // http client 라이브러리를 Netty 사용
            // 네티 설정 추가 : 스레드 1개만 쓰도록 설정 (default : 프로세스 갯수(CPU 코어 갯수) * 2)
            // 병렬적 수행으로 변경
            new Netty4ClientHttpRequestFactory(new NioEventLoopGroup(1)));

    /**
     * http://localhost:8080/async/home?idx=2
     * 1) RestTemplate : 비동기 호출
     * @param idx
     * @throws InterruptedException
     */
    @GetMapping("/home")
    public void loadTest(int idx) throws InterruptedException {
        restTemplateFacade.loadTest("http://localhost:8080/async/step1?idx=" + idx);
    }

    @GetMapping("/step1")
    public String rest(int idx) {
        /* ListenableFuture : 비동기 처리의 결과 값을 사용할 수 있는 callback을 추가 */
        ListenableFuture<ResponseEntity<String>> f1 =
                rt.getForEntity(SERVICE_URL1,
                        String.class,
                        "hello" + idx);

        f1.addCallback(s -> {
            log.info(s.toString());
        }, e -> {
            log.error(e.getMessage());
        });

        log.info("forObject...");
        return f1.toString();
    }
}
