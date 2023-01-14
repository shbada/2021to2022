package com.example.async.interfaces.callbackHell;

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
import org.springframework.web.context.request.async.DeferredResult;

/**
 * [Thread Pool Hell]
 * 스프링의 비동기 기술 을 이용해 클라이언트로부터 요청을 받은 후 실제 작업은 작업 스레드 풀에 위임하고,
 * 현재의 서블릿 스레드는 서블릿 스레드 풀에 반환 후, 다음 요청이 들어올 경우 바로 사용할 수 있게 효율적으로 처리
 *
 * Thread Pool Hell이란 풀 안에 있는 스레드에 대한 사용 요청이 급격하게 증가해 추가적인 요청이 들어올 때,
 * 사용 가능한 스레드 풀의 스레드가 없기 때문에 대기 상태에 빠져 요청에 대한 응답이 느려지게 되는 상태
 *
 *  하나의 요청에 대한 처리를 수행하면서 외부의 서비스들을 호출하는 작업이 많이 있는 경우, 문제는 단순히 비동기를 서블릿을 사용하는 것만으로 해결할 수 없다.
 * 비동기 서블릿을 사용하더라도 하나의 요청을 처리하는 동안 하나의 작업(워커) 스레드는 그 시간동안 대기상태에 빠지게 되어 결국에는 스레드 풀의 가용성이 떨어지게된다.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hell")
public class CallbackHellController {
    private final RestTemplateFacade restTemplateFacade;

    static final String SERVICE_URL1 = "http://localhost:8081/service?req={req}";
    static final String SERVICE_URL2 = "http://localhost:8081/service2?req={req}";

    static final String STEP2_SERVICE_URL1 = "http://localhost:8081/step2/service?req={req}";
    static final String STEP2_SERVICE_URL2 = "http://localhost:8081/step2/service2?req={req}";

    AsyncRestTemplate rt = new AsyncRestTemplate(
            new Netty4ClientHttpRequestFactory(new NioEventLoopGroup(1)));


    @GetMapping("/home")
    public void loadTest(int idx) throws InterruptedException {
        restTemplateFacade.loadTest("http://localhost:8080/hell/call?idx=" + idx);
    }

    /**
     * 콜백 헬
     * = 외부 서비스의 요청에 대한 결과를 다시 다른 서비스를 호출하는 요청의 파라미터로 사용하면서 콜백의 구조가 복잡해지는 문제 발생
     * @param idx
     * @return
     */
    @GetMapping("/call")
    public DeferredResult<String> restCallbackHell(int idx) {
        // 오브젝트를 만들어서 컨트롤러에서 리턴하면 언제가 될지 모르지만 언제인가 DeferredResult에 값을 써주면
        // 그 값을 응답으로 사용
        DeferredResult<String> dr = new DeferredResult<>();

        ListenableFuture<ResponseEntity<String>> f1
                = rt.getForEntity(STEP2_SERVICE_URL1, String.class, "hello" + idx);

        f1.addCallback(s -> {
            ListenableFuture<ResponseEntity<String>> f2
                    = rt.getForEntity(STEP2_SERVICE_URL2, String.class, s.getBody());

            f2.addCallback(s2 -> {
                dr.setResult(s2.getBody());
            }, e -> {
                dr.setErrorResult(e.getMessage());
            });

        }, e -> {
            dr.setErrorResult(e.getMessage());
        });

        return dr;
    }
}
