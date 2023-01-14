package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 자바 클래스는 모두 extends Object 가 되어있으므로
 * Object 타입으로 조회하면 모든 스프링 빈이 조회된다.
 * (부모 타입으로 조회하면 자식 타입도 함께 조회된다)
 */
public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로만 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
        // AppConfig.java 수정 없이 처리하자.
        // ac.getBean(DiscountPolicy.class)

        // bean 이 2개인데 1개만 받으니 에러 발생
        // MemberRepository bean = ac.getBean(MemberRepository.class);

        // 에러가 발생해야 true
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로만 조회시 같은 타입이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    void findBeanByName() {
        MemberRepository memberService = ac.getBean("memberRepository1", MemberRepository.class);
        //Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findBeanByType() {
        Map<String, MemberRepository> map = ac.getBeansOfType(MemberRepository.class);
        map.keySet().stream()
                .map(key -> "key = " + key + " value = " + map.get(key))
                .forEach(System.out::println);

        Assertions.assertThat(map.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig { // ApplicationContextSameBeanFindTest 클래스 안에서만 쓰겠다는 의미
        /* 같은 타입의 2개 빈 생성 */
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
