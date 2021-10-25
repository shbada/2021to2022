package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

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
