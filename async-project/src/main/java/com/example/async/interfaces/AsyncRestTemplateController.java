package com.example.async.interfaces;

import com.example.async.application.RestTemplateFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/async")
public class AsyncRestTemplateController {
    private final RestTemplateFacade restTemplateFacade;

    static final String SERVICE_URL1 = "http://localhost:8081/service?req={req}";
    static final String SERVICE_URL2 = "http://localhost:8081/service2?req={req}";

    // asynchronous
    AsyncRestTemplate rt = new AsyncRestTemplate();

    /**
     * http://localhost:8080/async/home?idx=2
     * 1) AsyncRestTemplate : 비동기 호출
     * @param idx
     * @throws InterruptedException
     */
    @GetMapping("/home")
    public void loadTest(int idx) throws InterruptedException {
        restTemplateFacade.loadTest("http://localhost:8080/async/step1?idx=" + idx);
    }

    @GetMapping("/home2")
    public void loadTest2(int idx) throws InterruptedException {
        restTemplateFacade.loadTest("http://localhost:8080/async/step2?idx=" + idx);
    }

    /**
     * 스프링은 컨트롤러에서 ListenableFuture를 리턴하면 해당 스레드는 즉시 반납하고,
     * 스프링 MVC가 자동으로 등록해준 콜백에 의해 결과가 처리
     * @param idx
     * @return
     */
    @GetMapping("/step1")
    public ListenableFuture<ResponseEntity<String>> rest(int idx) {
        /*
             tomcat 스레드는 요청에 대한 작업을 다 끝내기 전에 반환을 해서 바로 다음 요청을 처리하도록 사용합
             그리고 외부 서비스로부터 실제 결과를 받고 클라이언트의 요청에 응답을 보내기 위해서는 새로운 스레드를 할당 받아 사용

             AsyncRestTemplate은 비동기 클라이언트를 제공하는 클래스이며 ListenableFuture를 반환
             스프링은 컨트롤러에서 ListenableFuture를 리턴하면 해당 스레드는 즉시 반납하고,
             스프링 MVC가 자동으로 등록해준 콜백에 의해 결과가 처리

             ListenableFuture : 비동기 처리의 결과 값을 사용할 수 있는 callback을 추가
         */
        return rt.getForEntity(SERVICE_URL1,
                String.class,
                "hello" + idx);
    }

    @GetMapping("/step2")
    public String rest2(int idx) {
        /*
             tomcat 스레드는 요청에 대한 작업을 다 끝내기 전에 반환을 해서 바로 다음 요청을 처리하도록 사용합
             그리고 외부 서비스로부터 실제 결과를 받고 클라이언트의 요청에 응답을 보내기 위해서는 새로운 스레드를 할당 받아 사용

             AsyncRestTemplate은 비동기 클라이언트를 제공하는 클래스이며 ListenableFuture를 반환
             스프링은 컨트롤러에서 ListenableFuture를 리턴하면 해당 스레드는 즉시 반납하고,
             스프링 MVC가 자동으로 등록해준 콜백에 의해 결과가 처리

             ListenableFuture : 비동기 처리의 결과 값을 사용할 수 있는 callback을 추가
         */
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
