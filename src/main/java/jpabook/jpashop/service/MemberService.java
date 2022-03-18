package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 트랜잭션 필수 (public method 모두 트랜잭션에 걸린다.)
@Transactional(readOnly = true) // 트랜잭션 필수 (읽기가 중복이 많아서 이렇게 쓰고, 쓰기에는 별도로 메소드 단위로 선언한다.)-
/*
 * javax Transactional
 * spring Transactional (권장 - 이미 스프링 프레임워크를 쓰기 때문에)
 */
@RequiredArgsConstructor
public class MemberService {
    /* 필드 주입
    @Autowired
    private MemberRepository memberRepository;
    */

    /* setter 주입 (단점 - 런타임 시점에 setMemberRepository 를 호출하여 바꿀 수가 있다)
    private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    /** 생성자 주입 (권장) */
    // final : 컴파일 시점에 생성자 주입 체크가 가능하기 때문에 써주는걸 권장한다.
    private final MemberRepository memberRepository;

    // 생성자가 하나만 있는 경우 @Autowired 어노테이션이 없어도 자동으로 주입해줌
    // 또는 롬복 사용지 아래 코드 생략 가능 (위 MemberService 위에 @RequiredArgsConstructor : final 필드만 생성자 만들어줌)
    /*
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /**
     * 회원가입
     * @param member
     * @return
     */
    @Transactional // 메소드 위에 트랜잭션 어노테이션이 우선 적용
    public Long join(Member member) {
        // 회원 이름 중복체크
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId(); // 항상 값이 있다는 보장이 있음
    }

    /**
     * 중복회원 체크
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        // exception
        List<Member> findMembers = memberRepository.findByName(member.getName()); // DB unique 설정 권장 (동시 insert시에도 방어)

        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     * @return
     */
    // @Transactional(readOnly = true) // JPA 가 조회하는 곳에서는 성능을 최적화한다. (읽기에 쓴다)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     * @param memberId
     * @return
     */
    // @Transactional(readOnly = true) // JPA 가 조회하는 곳에서는 성능을 최적화한다. (읽기에 쓴다)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
