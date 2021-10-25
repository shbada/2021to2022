package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
}
