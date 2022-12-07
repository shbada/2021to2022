package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.getCount();

        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.getCount();

        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

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
        // 프로토타입 빈 주입
        private final PrototypeBean prototypeBean;

        // 생성 시점에 주입됨
        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

        /* 프로토타입 성격을 살리기 위한 지저분한 코드 */
//        @Autowired
//        ApplicationContext applicationContext;
//
//        public int logic() {
        // 스프링 컨텍스트에 너무 종송적이다.
        // 단위 테스트도 어렵다.
        // 이렇게 직접 필요한 의존관계를 찾는것을 DL(Dependency Lookup)이라고 한다.
        // DL 정도의 기능 제공만 해주는 무언가가 있으면 된다... -> 스프링에 준비되어있다.
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }
    }
}
