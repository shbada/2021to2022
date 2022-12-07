package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    // Object: controller 버전 상관없이 들어올 수 있다.
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
//    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    private final List<MyHandlerAdapter> handlerAdapterList = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapterList.add(new ControllerV3HandlerAdapter());
        handlerAdapterList.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * 1) handlerMappingMap 안의 value 를 꺼낸다.
         * 2) handler 객체로 MyHandlerAdapter 를 꺼낸다.
         * 3) handle() 가 호출되어 Controller 로직이 수행된다.
         * 4) view 로직을 수행한다.
         */
        /* 처리할 수 있는 handlerAdapter 을 찾는다. */
        Object handler = getHandler(request); // 현재 handler : MemberFormControllerV3

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        /* MyHandlerAdapter 을 가져온다 */
        // 현재 handler : MemberFormControllerV3
        MyHandlerAdapter adapter = getHandlerAdapter(handler); // ControllerV3HandlerAdapter

        ModelView mv = adapter.handle(request, response, handler);

        // paramMap
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    /**
     * MyHandlerAdapter 얻기
     * @param handler
     * @return
     */
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;

        // handler : MemberFormControllerV3
        // ControllerV3HandlerAdapter
        for (MyHandlerAdapter adapter : handlerAdapterList) {
            if (adapter.supports(handler)) { /* supports true 일 경우 */
                return adapter; // ControllerV3HandlerAdapter
            }
        }

        throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다. handler = " + handler);
    }

    /**
     * return MyView
     * @param viewName
     * @return
     */
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    /**
     * MemberFormControllerV3 등을 꺼낸다.
     * @param request
     * @return
     */
    private Object getHandler(HttpServletRequest request) {
        /* map get */
        String requestURI = request.getRequestURI();

        // 부모로 받을 수 있음.
        return handlerMappingMap.get(requestURI);
    }
}
