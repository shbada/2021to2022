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
     * set 메서드 주입
     * 필드에 final 제거하고 set 메서드로 선언
     * @Autowired 명시되어있으므로 해당 매개변수 타입에 맞게 주입
     * 선택/변경 가능성이 있는 의존관계에 사용
     *
     * (생성자에서는 필수값인데, set 은 매개변수가 빈으로 등록 안되어있을 경우에도 설정 가능하다)
     * @Autowired(required = false)
     */
    /**
     * 생성자 주입
     * 이름 그대로 생성자를 통해서 의존 관계를 주입받는 방법이다.
     * 생성자 호출 시점에 딱 1번만 호출되는 것이 보장된다.
     * 불변/필수 의존관계에 사용
     * 자바 스프링이 OrderServiceImpl 객체를 생성할때 생성자가 실행되니깐 그때 주입도 동시에 발생
     */
    /**
     * 의존관계 자동 주입
     * memoryMemberRepository, rateDiscountPolicy 를 찾아서 자동 주입 해주는것. (타입으로 찾는다)
     * 생성자가 1개이면 @Autowired 생략 가능
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
