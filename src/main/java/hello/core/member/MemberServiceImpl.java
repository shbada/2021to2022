package hello.core.member;

public class MemberServiceImpl implements MemberService {
    /**
     * (문제점) MemoryMemberRepository 에 의존하고 있음
     * 추상화 MemberRepository, 구체화 MemoryMemberRepository 모두에 의존적이다.
     */
    // MemoryMemberRepository 로 지정
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // AppConfig 추가 후 아래 생성자 주입으로 변경
    // 추상화에만 의존하게되었다.
    private final MemberRepository memberRepository;

    /* 의존관계를 마치 외부에서 주입해주는 것 같다. -> DI (Dependency Injection) 이라고 한다 */
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     */
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    /**
     * 회원 단건 조회
     * @param memberId
     * @return
     */
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
