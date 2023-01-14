package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        // required = false : 자동 주입할 대상이 없으면 아예 호출이 안된다.
        @Autowired(required = false) // (required = ture)일땐 빈 못찾는다고 에러 발생
        public void setNoBean1(Member noBean1) { // Member 은 빈이 아님
            System.out.println("noBean1 = " + noBean1);
        }

        // null
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { // Member 은 빈이 아님
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional.empty
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) { // Member 은 빈이 아님
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
