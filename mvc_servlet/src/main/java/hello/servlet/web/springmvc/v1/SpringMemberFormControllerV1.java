package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Controller
 * 스프링이 자동으로 스프링 빈으로 등록한다.
 * (@Component 가 있으면 자동으로 스프링의 컴포넌트 대상인데, @Controller 안에 @Component 가 있다)
 * 스프링 MVC 에서 어노테이션 기반 컨트롤러로 인식한다.
 *
 * @RequestMapping : 요청 정보를 매핑한다.
 */
@Controller

/* @Controller 대신 사용 가능 */
// @Component // 컴포넌트 스캔 대상 또는 얘를 생략하고 직접 @Bean 해서 등록해도된다.
// @RequestMapping // 클래스 레벨에 있으면 이거 덕분에 핸들러 매핑이 얘를 찾아낸다.
public class SpringMemberFormControllerV1 {

    /**
     * 회원 신규등록 화면
     * @return
     */
    @RequestMapping("/springmvc/v1/members/new-form") // 스프링이 지원하는 어노테이션 기반의 핸들러 매핑과 어댑터
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}