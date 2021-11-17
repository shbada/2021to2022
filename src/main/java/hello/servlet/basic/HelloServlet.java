package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * http://localhost:8080/hello 접속시 서블릿 호출
 */
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    /**
     * servlet 이 호출되면 아래 service 메서드가 호출된다.
     * @param request (웹 브라우저가 만들어준 객체로, 함께 전달됨)
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("req = " + request);
        System.out.println("resp = " + response);

        // http://localhost:8080/hello?username=kim
        String username = request.getParameter("username");
        System.out.println(username); // kim

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username); // body 에 들어감
    }
}
