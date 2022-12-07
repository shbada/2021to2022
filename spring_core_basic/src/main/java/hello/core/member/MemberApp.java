package hello.core.member;

import hello.core.AppConfig;

public class MemberApp {
    public static void main(String[] args) {
        /**
         * 테스트 코드로 작성하자. (Next)
         * package 경로가 중요하다
         */
        AppConfig appConfig = new AppConfig();
        /* AppConfig 에서 가져온 객체로 대체한다 */
        MemberService memberService = appConfig.memberService();
        // MemberService memberService = new MemberServiceImpl();

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
