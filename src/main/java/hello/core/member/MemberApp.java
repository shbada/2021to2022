package hello.core.member;

public class MemberApp {
    public static void main(String[] args) {
        /**
         * 테스트 코드로 작성하자. (Next)
         * package 경로가 중요하다
         */
        MemberService memberService = new MemberServiceImpl();
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
