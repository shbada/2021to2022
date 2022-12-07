package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Logging
 *
 * 로깅 라이브러리
 * SLF4J : 인터페이스
 * Logback : SLF4J 인터페이스의 구현체
 */

@RestController // String 반환시 String 그대로 반환됨 (=HTTP 메시지 바디에 바로 입력)
// @Controller // String view 이름이 반환됨
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        /* 그대로 모두 출력. 로그를 무조건 남겨버린다 */
        System.out.println("name= " + name); // 더이상 쓰면 안됨

        // 이렇게 쓰면 안된다. info 일땐 별 상관 없지만,
        log.info("info log=" + name);
        // trace 의 경우 : 아래는 출력이 안됨.
        // "trace my log=Spring" 연산이 일어나서 이걸 가지고있음
        // 연산이 일어나면서 메모리도 사용됨
        // trace 로그를 쓰지도 않는데 쓸모없는 리소스를 사용하게 되는 것이다.
        log.trace("trace log=" + name);

        /* level 지정에 따라 그 이후 레벨로 출력 */
        /* trace 일때 trace 이후로 출력, info 일때 info 이후로만 출력. info 가 운영에서 많이사용 */
        /* TRACE > DEBUG > INFO > WARN > ERROR */
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info(" info log= {}", name); // 이렇게 써야함
        log.warn(" warn log = {}", name);
        log.error("error log = {}", name);

        return "ok";
    }
}
