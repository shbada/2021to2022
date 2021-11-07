package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        // LifeCycleConfig bean 등록
        // ConfigurableApplicationContext 가 AnnotationConfigApplicationContext 의 상위이므로(부모) 댬을 수 있음.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);

        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        // 스프링 빈이 설정되면서 로직이 호출됨
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); // 객체 생성
            networkClient.setUrl("http://hello-spring.dev"); // 필요한 값 셋팅 (이때부터 url 이 존재함)
            
            /** 스프링 빈은 라이프사이클을 다음과 같이 가진다. [객체생성 -> 의존관계 주입] */
            // 객체를 다 생성해놔야 의존관계 주입을 하니깐. (생성자 주입은 예외, 객체가 생성될때 의존관계가 동시에 발생되니깐) , 이는 필드주입/세터주입만 해당
            // 스프링 빈은 객체를 생성하고 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비가 완료된다.
            // 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야한다. 그런데 개발자가 의존관계 주입이 모두 완료된 시점을 어떻게 알까?
            // 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공한다.
            // 또한 스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다.

            // 스프링 빈의 이벤트 라이프 사이클 (싱글톤의 예시)
            // [스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료

            // 초기화 콜백 : 빈이 생성되고, 빈의 의존 관계 주입이 완료된 후 호출
            // 소멸전 콜백 : 빈이 소멸되기 직전에 호출

            // 객체의 생성과 초기화를 분리하자.
            // 생성자는 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다.
            // 반면에 초기화는 이렇게 생성된 값들을 활용해서 웹 커넥션을 연결하는 등 무거운 동작을 수행한다.
            // 생성자 안에서 무거운 초기화 작업을 하는것 보다, 초기화부분을 객체 생성 부분과 명확하게 나누는것이 유지보수에 좋다.
            // 물론 초기화 자겅ㅂ이 내부 값들만 약간 변경하는 정도로 단순한 경우에는 생성자에서 한번에 처리하는게 나을수도 있다.

            // 스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원한다.
            // 인터페이스 (InitializingBean, DisposableBean)
            // 설정 정보에 초기화 메서드, 종료 메서드 지정
            // @PostConstruct, @PreDestory 애노테이션 지원

            return networkClient;
        }
    }
}
