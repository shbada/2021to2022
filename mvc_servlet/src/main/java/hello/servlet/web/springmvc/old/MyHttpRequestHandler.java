package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1) 핸들러 매핑으로 핸들러 조회 (빈 이름)
 * 2) 핸들러 어댑터를 뒤진다. (HttpRequestHandlerAdapter 에서 걸린다. -> supports() ; true)
 * 3) 핸들러 어댑터 실행 (MyHttpRequestHandler 내부에서 실행하고 그 결과를 반환한다.)
 */
@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
