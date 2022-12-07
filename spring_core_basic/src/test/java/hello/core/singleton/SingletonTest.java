package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.beanfind.ApplicationContextExtendsFindTest;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 참조값 다른것을 확인
        System.out.println(memberService1);
        System.out.println(memberService2);

        // 요청할때마다 새로운 객체를 생성하므로 두 객체는 다르다.
        // 메모리 낭비가 심하다.
        /** 해결방안) 해당 객체가 딱 1개만 생성되고, 공유하도록 설계해야한다 : 싱글톤 */
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void singletonContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회 : 호출할 때마다 객체 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값 다른것을 확인
        System.out.println(memberService1);
        System.out.println(memberService2);

        /**
         * 스프링 싱글톤 컨테이너
         * 빈 : 싱글톤 객체
         * 스프링 컨테이너 덕분에 고객의 요청이 올때마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유한다.
         */
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
