package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
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
/**
 * proxyMode = ScopedProxyMode.TARGET_CLASS 추가
 * 대상이 class, interface 에 따라 TARGET_** 으로 설정
 * 가짜 프록시 클래스를 만든다. 로그 찍으면 $$EnhancerBySpringCGLIB$$b2f5809a 라고 뜸 (프록시객체)
 * 그러고 실제 호출되는 시점에 진짜를 찾아서 동작한다.
 * 이렇게 하면 MyLogger 의 가짜 프록시 클래스를 만들어두고 HTTP request 와 상관 없이 가짜 프록시 클래스를 다른 빈에 미리 주입해 둘 수 있다.
 *
 * 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점이다.
 * 어노테이션 설정만으로 원본 객체를 프록시 객체로 대체할 수 있다.
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // request scope
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
