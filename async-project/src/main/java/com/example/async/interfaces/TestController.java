package com.example.async.interfaces;

import com.example.async.application.TestFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {
    private final TestFacade testFacade;

    static final String SERVICE_URL1 = "http://localhost:8081/service?req={req}";
    static final String SERVICE_URL2 = "http://localhost:8081/service2?req={req}";

    WebClient client = WebClient.create();

    @GetMapping("/test")
    public Mono<String> rest(int idx) {
        // Mono 리턴
        Mono<ClientResponse> res = client.get().uri(SERVICE_URL1, idx).exchange();
        Mono<String> body = res.flatMap(clientResponse -> clientResponse.bodyToMono(String.class));

        return body;
    }

    @GetMapping("/rest/chain")
    public Mono<String> restChain(int idx) {
        Mono<String> body = client.get().uri(SERVICE_URL1, idx).exchange()
                .flatMap(c -> c.bodyToMono(String.class))
                .doOnNext(c -> log.info(c))
                .flatMap(res1 -> client.get().uri(SERVICE_URL2, res1).exchange())
                .flatMap(c -> c.bodyToMono(String.class)) // Mono<String>
                .doOnNext(c -> log.info(c))
                .flatMap(res2 -> Mono.fromCompletionStage(testFacade.work(res2)))
                ;

        return body;
    }
}
