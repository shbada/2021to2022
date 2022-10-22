//package com.example.async.interfaces;
//
//import com.example.async.application.TestFacade;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.function.client.ClientResponse;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.concurrent.Callable;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//@RequestMapping("/test")
//public class TestController {
//    private final TestFacade testFacade;
//
//    static final String SERVICE_URL1 = "http://localhost:8081/service?req={req}";
//    static final String SERVICE_URL2 = "http://localhost:8081/service2?req={req}";
//
//    WebClient client = WebClient.create();
//
//    @GetMapping("/home")
//    public Mono<String> rest(int idx) {
//        // Mono 리턴
//        Mono<ClientResponse> res = client.get().uri(SERVICE_URL1, idx).exchange();
//        Mono<String> body = res.flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
//
//        return body;
//    }
//
//    @GetMapping("/rest_chain")
//    public Mono<String> restChain(int idx) {
//        Mono<String> body = client.get().uri(SERVICE_URL1, idx).exchange()
//                .flatMap(c -> c.bodyToMono(String.class))
//                .doOnNext(c -> log.info(c))
//                .flatMap(res1 -> client.get().uri(SERVICE_URL2, res1).exchange())
//                .flatMap(c -> c.bodyToMono(String.class)) // Mono<String>
//                .doOnNext(c -> log.info(c))
//                .flatMap(res2 -> Mono.fromCompletionStage(testFacade.work(res2)))
//                ;
//
//        return body;
//    }
//}
