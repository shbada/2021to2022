package hello.core.member;

public interface MemberService {
    /** 회원가입 */
    void join(Member member);

    /** 회원 단건 조회 */
    Member findMember(Long memberId);
}
