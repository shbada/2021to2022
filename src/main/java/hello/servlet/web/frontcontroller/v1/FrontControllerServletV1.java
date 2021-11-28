package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * frontController 패턴
 * 1) 프론트 컨트롤러 서블릿 하나로 클라이언트의 요청을 받음
 * 2) 요청에 맞는 컨트롤러 찾아서 호출
 * 3) 입구를 하나로
 * 4) 공통 처리 가능
 * 5) 프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도됨
 *
 * 스프링 웹 MVC 의 핵심 : FrontController
 * 스프링 웹 MVC 의 DispatcherServlet 이 FrontController 패턴으로 구현되어있음
 *
 * 구조를 변경할때는 세부적인건 그대로 두고,
 * 구조를 변경한 후에 세부적인 코드도 리팩토링
 */
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        /* map get */
        String requestURI = request.getRequestURI();

        // 부모로 받을 수 있음.
        // ControllerV1 controller = new MemberFormControllerV1();
        ControllerV1 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        /* 요청에 맞는 컨트롤러의 process 메서드 수행 */
        controller.process(request, response);
    }
}
