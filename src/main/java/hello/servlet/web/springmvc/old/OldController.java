package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * URL 로 호출하면 정상 호출된다.
 *
 * 핸들러매핑에서 OldController 를 찾아온다.
 * 핸들러 어댑터 를 찾아온다. (핸들러 매핑을 통해서 찾은 핸들러를 실행할 수 있는 핸들러 어댑터가 필요하다.)
 * (Controller 인터페이스를 실행할 수 있는 핸들러 어댑터)
 *
 * 스프링 부트
 *
 * [Handler Mapping]
 * @RequestMapping : 0순위
 * BeanNameUrlHandlerMapping : 1순위 (스프링 빈의 이름으로 핸들러를 매칭한다.)
 *
 * [Handler Adapter]
 * RequestMappingHandlerMapping : 0순위 (@RequestMapping)
 * HttpRequestHandlerAdapter : 1순위 (HttpRequestHandler 처리)
 * SimpleCOntrollerHandlerAdapter : 2순위 (Controller 인터페이스 처리)
 */
@Component("/springmvc/old-controller") /* 빈 이름 설정 */
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return null;
    }
}
