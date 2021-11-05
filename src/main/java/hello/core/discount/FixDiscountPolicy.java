package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // 문제 발생! RateDiscountPolicy 랑 타입이 동일, DI 주입시 타입으로 주입할때 빈이 중복됨.
// 해결 방법 : @Autowired, @Quilifier, @Primary
//@Qualifier("fixDiscountPolicy") // 구분 기준을 추가한다.
public class FixDiscountPolicy implements DiscountPolicy {
    private int discountfixAmount = 1000; // 1000원 할인의 의미

    @Override
    public int discount(Member member, int price) {
        // enum 은 == 이다 (equals 아니다.)
        if (member.getGrade() == Grade.VIP) {
            return discountfixAmount;
        } else {
            return 0;
        }
    }
}
