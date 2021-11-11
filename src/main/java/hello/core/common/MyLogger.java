package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * request scope
 * HTTP 요청 당 하나씩 생성됨
 * uuid 를 저장해두면 다른 HTTP 요청과 구분이 가능하다.
 * requestURL 필드는 이 빈이 생성되는 시점을 알 수 없으므로, 외부에서 setter 로 입력받는다.
 */
@Component
@Scope(value = "request") // request scope
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " +  message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this); // 주소까지
    }

    @PreDestroy // request scope 호출됨
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this); // 주소까지

    }
}
