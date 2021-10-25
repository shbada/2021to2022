package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*; // static 선언

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 는 10% 할인이 적용되어야한다.") // 한글 입력 가능
    void vip_o() {
        // given
        /* 1) Member 등록 */
        Long memberId = 1L; // long type 선언시 null 불가능 (Wrapper 타입 사용해서 NULL 도 가능하도록 함)
        Member member = new Member(memberId, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP 가 아니면 10% 할인이 적용되지 않아야한다.") // 한글 입력 가능
    void vip_x() {
        // given
        /* 1) Member 등록 */
        Long memberId = 1L; // long type 선언시 null 불가능 (Wrapper 타입 사용해서 NULL 도 가능하도록 함)
        Member member = new Member(memberId, "memberBASIC", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(0); // basic 은 할인이 안되므로 0
    }

}