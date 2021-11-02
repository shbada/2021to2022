package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 가변 할인율로 변경 (FixDiscountPolicy -> RateDiscountPolicy 로 변경)
    // 여기서.. 클라이언트인 OrderServiceImpl 의 코드 수정이 발생한 것임 (의존되어있음 -> 문제점)
    // 직접 구체 클래스를 선택했기 때문
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 그래서 아래 코드로 다시 변경 (추상클래스에 의존하자. (인터페이스))
    // 이렇게만 두면.. NullPointerException 발생한다.
    // 해결을 위해. 누군가, 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해주어야한다. (중요!)
    // AppConfig 의 등장 = "구현 객체를 생성"하고 "연결"하는 책임을 가지는 별도의 설정 클래스
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    // 파라미터 객체에 어떤 구현클래스가 들어올지는 클라이언트가 전혀 모른다.
    // 추상화 인터페이스의 존재만 알 뿐이다.

    /**
     * 의존관계 자동 주입
     * memoryMemberRepository, rateDiscountPolicy 를 찾아서 자동 주입 해주는것. (타입으로 찾는다)
     * @param memberRepository
     * @param discountPolicy
     */
    @Autowired // 생성할때 MemberRepository, DiscountPolicy 타입에 맞게 의존 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    /**
     * 주문 생성
     * @param memberId
     * @param itemName
     * @param itemPrice
     * @return 주문 결과
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        /* 단일 체계 원칙 */
        Member member = memberRepository.findById(memberId);

        // 할인은 discountPolicy 가 알아서 한다. member 는 모른다.
        // 할인 관련 수정이 발생하면 member, order 엔 수정이 없다.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 주문 결과 리턴
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
