package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Primary // 빈이 중독됬을때 무조건 선택
@MainDiscountPolicy // 직접 만든 어노테이션 (@Qualifier)
//@Qualifier("mainDiscountPolicy") // 구분 기준을 추가한다.
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        // enum 은 == 이다 (equals 아니다.)
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
