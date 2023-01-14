package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 어노테이션 권장 방법
 * 1) @PostConstruct 생성
 * 2) @PreDestroy 소멸
 *
 * - 최신 스프링에서 가장 권장하는 방법이다.
 * - 어노테이션 하나만 붙이면 되므로 매우 편리하다.
 * - JSR-250 이라는 자바 표준이다. (스프링이 아닌 다른 컨테이너에서도 동작함)
 * - 컴포넌트 스캔과 잘어울림
 * - 유일한 단점 : 외부 라이브러리에 적용하지 못한다. (외부 라이브러리의 초기화/종료는 @Bean 설정으로 지정하자)
 */
public class NetworkClient {

    private String url;

    public NetworkClient() {
        //System.out.println("생성자 호출, url = " + url);
        //connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    //
    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
