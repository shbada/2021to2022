package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request, HttpServletResponse response,
                          HttpMethod httpMethod /* GET */, Locale locale, /* ko_KR */
                          @RequestHeader MultiValueMap<String, String> headerMap, /* header 한번에 받기 */
                          @RequestHeader("host") String host, /* header 1개만 받기 */
                          @CookieValue(value = "myCookie", required = false) String cookie) { /* 쿠키명 myCookie */
        /**
         * MultiValueMap
         * MAP과 유사한데, 하나의 키에 여러 값을 받을 수 있다.
         * HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용한다.
         * keyA=value1&keyA=value2
         *
         // [value1,value2]
         List<String> values = map.get("keyA");
         */
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}
