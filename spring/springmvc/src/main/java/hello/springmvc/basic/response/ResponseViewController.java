package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ResponseViewController {

    /**
     * @ResponseBody 가 없으면 response/hello 로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링 한다.
     * @return
     */
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        /* templates/response/hello.html */
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    /**
     * @ResponseBody 가 있으면 문자열로 출력됨
     * @ResponseBody 가 없어야 view 를 찾는다.
     * @param model
     * @return
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!!");

        /* templates/response/hello.html */
        return "response/hello";
    }

    /**
     * @Controller 를 사용하고, HttpServletResponse , OutputStream(Writer) 같은 HTTP 메시지
     * 바디를 처리하는 파라미터가 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용
     *
     * 권장 X!!!!
     * @param model
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        /*
        요청 URL: /response/hello
        실행: templates/response/hello.html
         */
        model.addAttribute("data", "hello!!");
    }

    /**
     * thymeleaf
     * `implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'`
     *
     * 스프링 부트가 자동으로 ThymeleafViewResolver 와 필요한 스프링 빈들을 등록한다.
     * 그리고 다음 설정도 사용한다. 이 설정은 기본 값 이기 때문에 변경이 필요할 때만 설정하면 된다.
     *
     * spring.thymeleaf.prefix=classpath:/templates/
     * spring.thymeleaf.suffix=.html
     */
}
