package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * 요청 파라미터와 다르게, HTTP 메시지 바디를 통해 데이터가 직접 데이터가 넘어오는 경우는
 * @RequestParam , @ModelAttribute 를 사용할 수 없다.
 * (물론 HTML Form 형식으로 전달되는 경우는 요청 파라미터로 인정된다.)
 * 먼저 가장 단순한 텍스트 메시지를 HTTP 메시지 바디에 담아서 전송하고, 읽어보자.
 * HTTP 메시지 바디의 데이터를 InputStream 을 사용해서 직접 읽을 수 있다.
 */
@Slf4j
@Controller
public class RequestBodyStringController {
    /**
     * http://localhost:8080/request-body-string-v1
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    /**
     * 직접 받을 수 있다.
     * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter)
            throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity: HTTP header, body 정보를 편리하게 조회
     * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     * 응답에서도 HttpEntity 사용 가능
     - 메시지 바디 정보 직접 반환(view 조회X)
     - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     * HttpEntity 를 상속받은 다음 객체들도 같은 기능을 제공
     * 1) RequestEntity
     - HttpMethod, url 정보가 추가, 요청에서 사용
     * 2) ResponseEntity
     - HTTP 상태 코드 설정 가능, 응답에서 사용
     - return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED)
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");
    }

    /**
     * RequestEntity
     * ResponseEntity
     * @param httpEntity
     * @return
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3_2(RequestEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);
        return new ResponseEntity<String>("Hello World", HttpStatus.CREATED);
    }

    /**
     * @RequestBody
     * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     * - 헤더 정보가 필요하다면 HttpEntity 를 사용하거나 @RequestHeader 를 사용하면 된다.
     *
     * @ResponseBody
     * - 메시지 바디 정보 직접 반환(view 조회X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return "ok";
    }

    /**
     * 정리
     *
     * 이렇게 메시지 바디를 직접 조회하는 기능은 요청 파라미터를 조회하는 @RequestParam , @ModelAttribute 와는 전혀 관계가 없다.
     * 요청 파라미터를 조회하는 기능: @RequestParam, @ModelAttribute
     * HTTP 메시지 바디를 직접 조회하는 기능: @RequestBody
     */
}
