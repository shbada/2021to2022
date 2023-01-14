package hello.core.member;

import hello.core.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberSpringApp {
    public static void main(String[] args) {
        /**
         * 스프링은 모든게 ApplicationContext 에서 시작함. (모든 객체를 관리해줌)
         */
        // 스프링이 AppConfig 에 있는 환경 설정을 가지고 다 등록하고 관리해준다.
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);

        // Bean 이름이 memberService 이고, 타입이 Membe고Service 인걸 찾는다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // Long Type (1L)
        Member member = new Member(1L, "memberA", Grade.VIP);

        /* insert */
        memberService.join(member);

        /* select */
        Member findMember = memberService.findMember(1L);

        /*
            New Member = memberA
            findMember : memberA
         */
        System.out.println("New Member = " + member.getName());
        System.out.println("findMember : " + findMember.getName());
    }
}
