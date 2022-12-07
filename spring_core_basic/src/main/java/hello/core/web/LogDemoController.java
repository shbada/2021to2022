package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;

    /**
     * 에러 발생
     * 스프링 컨테이너가 떠서, MyLogger 빈으로 등록을 시도한다.
     * myLogger 은 scope 가 request 이다.
     * request 라서 생성 시점이 요청이 들어올때이므로, 웹 시작 시점엔 없다.
     * 그러므로, Provider 을 써야한다.
     */
    // private final MyLogger myLogger;

    // ObjectProvider (생성시점 오류 해결)
    private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        /* 컨트롤러에 고객 요청이 왔을 때 request scope 를 사용할 수 있는 상황이 되므로, 이때 꺼낸다. */
        // 이때 init() 이 호출된다.
        // 중요한거) LogDemoService 에서도 아래처럼 호출하는데, 이 빈은 모두 동일하다.
        MyLogger myLogger = myLoggerObjectProvider.getObject();

        String requestURL = request.getRequestURL().toString();

        // 이런건 사실 컨트롤러 보다는 공통 처리가 가능한 스프링 인터셉터나 서블릿 필터같은 곳에서 활용하는게 좋다.
        // 연습용이므로 우선 컨트롤러에 구현한다.
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");

        Thread.sleep(1000); // 요청이 섞여도 구분이 가능하다.

        logDemoService.logic("testid");
        return "OK";
    }
}
