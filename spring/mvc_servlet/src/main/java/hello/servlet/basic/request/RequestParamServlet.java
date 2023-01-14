package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 *
 * GET/POST 둘다 가능
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");

        /**
         * getParameter()는 GET(쿼리 파라미터)/POST(POST HTML Form) 둘다 지원한다.
         * GET URL 쿼리 파라미터 형식 : HTTP 메시지 바디를 사용하지 않기 때문에 content-type이 없다.
         */
        // username=hello
        // age=20
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(request.getParameter(paramName)));

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username"); // 여러가지 이름이 있을때는 순서로 첫번째껄 가져옴
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);

        // http://localhost:8080/request-param?username=hello&age=20&username=hello2
        String[] usernames = request.getParameterValues("username"); // 배열로 꺼내면 된다.
        for (String s : usernames) {
            System.out.println("usernames target : " + s);
        }

        response.getWriter().write("ok");

        /**
         * 복수 파라미터에서 단일 파라미터 조회
         * username=hello&username=kim
         * getParameter()는 하나의 파라미터 이름에 대해서 단 하나의 값만 있을때 사용한다.
         * 두개 이상일 경우 getParameterValues()를 사용해서 배열로 받아야한다.
         */
    }
}
