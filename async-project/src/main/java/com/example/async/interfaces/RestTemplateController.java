package com.example.async.interfaces;

import com.example.async.application.RestTemplateFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Async Servlet은 클라이언트로부터 요청을 받은 후 실제 작업은 작업 스레드 풀에 위임하고,
 * 현재의 서블릿 스레드는 서블릿 스레드 풀에 반환 후, 다음 요청이 들어올 경우 사용할 수 있도록한다.
 * 이에 반해, Sync Servlet은 요청을 받은 서블릿 스레드에서 실제 작업까지 전부 진행하기 때문에
 * 요청에 대한 응답을 반환하기 전까지는 새로운 요청을 처리할 수 없는 상태다.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/sync")
public class RestTemplateController {
    private final RestTemplateFacade restTemplateFacade;

    static final String SERVICE_URL1 = "http://localhost:8081/service?req={req}";
    static final String SERVICE_URL2 = "http://localhost:8081/service2?req={req}";

    static final String STEP2_SERVICE_URL1 = "http://localhost:8081/step2/service?req={req}";
    static final String STEP2_SERVICE_URL2 = "http://localhost:8081/step2/service2?req={req}";

    static final String CALLABLE_URL1 = "http://localhost:8081/callable";
    static final String CALLABLE_URL2 = "http://localhost:8081/callable2";

    // 블로킹 방식
    RestTemplate rt = new RestTemplate();

    /**
     * http://localhost:8080/sync/home?idx=2
     * 1) RestTemplate : 블로킹 호출
     * Spring에서 제공하는 RestTemplate을 이용해 100개의 Request를 동시에 호출
     * @param idx
     * @throws InterruptedException
     */
    @GetMapping("/home")
    public void loadTest(int idx) throws InterruptedException {
        restTemplateFacade.loadTest("http://localhost:8080/sync/step1?idx=" + idx);
    }

    @GetMapping("/step1")
    public String rest(int idx) {
        /*
            MainApplication의 tomcat 스레드는 클라이언트의 요청을 처리하며,
            외부 서비스(RemoteApplication)로 요청(Network I/O)을 보낸 후, 응답이 올 때까지 대기하고 있는 상태
            (외부 서비스로부터의 요청에 대한 응답을 받기 전까지는 blocking 상태)
         */
        String forObject = rt.getForObject(SERVICE_URL1,
                String.class,
                "hello" + idx);

        log.info("forObject : " + forObject);
        return forObject;
    }

    @GetMapping("/callable")
    public void callable() throws InterruptedException {
        restTemplateFacade.loadTest(CALLABLE_URL1);
    }

    @GetMapping("/callable2")
    public void callable2() throws InterruptedException {
        restTemplateFacade.loadTest(CALLABLE_URL2);
    }
}
