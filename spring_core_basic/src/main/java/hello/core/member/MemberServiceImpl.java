package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 스프링빈 자동 등록 MemberServiceImpl
// 의존관계 주입은 어떻게 해줄것? 생성자 위에 @Autowired 추가
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

    /**
     * 의존관계 자동주입 @Autowired
     * 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다. (memoryMemberRepository(빈이름);MemoryMemberRepository(빈객체))
     * 타입이 맞으니 memoryMemberRepository 를 찾아온것 (타입이 같은게 여러개면? 방법이 있음)
     *
     * 기본조회 전략; 타입이 같은 빈을 찾아서 주입한다.
     * @param memberRepository
     */
    @Autowired // MemberRepository 타입에 맞는애를 가져와서 자동 주입한다. ac.getBean(MemberRepository.class) 와 동일하게 동작
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
