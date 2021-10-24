package hello.core.member;

public class MemberServiceImpl implements MemberService {
    /**
     * (문제점) MemoryMemberRepository 에 의존하고 있음
     * 추상화 MemberRepository, 구체화 MemoryMemberRepository 모두에 의존적이다.
     */
    // MemoryMemberRepository 로 지정
    private final MemberRepository memberRepository = new MemoryMemberRepository();

//    public MemberServiceImpl(MemoryMemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

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
}
