package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

// JSR303 Provider
// 의존성 implementation 'javax.inject:javax.inject:1' 추가
public class SingletonWithPrototypeTest3 {

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destory() {
            System.out.println("PrototypeBean.destory");
        }
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        // 계속 같은 Bean
        // 주입된 빈을 같이 사용한다.
        // 프로토타입 빈은 사용할때마다 새로 생성해서 사용하길 원할 것이다. 주입 시점에만 실행되는거 말구.
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        Assertions.assertThat(count2).isEqualTo(2);

    }

    @Scope("singleton") // default
    static class ClientBean {
        // implementation 'javax.inject:javax.inject:1' 추가 후 사용 가능
        // 스프링이 아니여도 사용 가능
        // 라이브러리 의존성 추가 필요
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // 심플
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

        /**
         * 프로토타입 필요할 경우 : 매번 사용할때마다 의존관계 주입이 완료된 새로운 객체가 필요할 경우 사용하자.
         * 실무에선 매우 드물다.
         */
    }
}
