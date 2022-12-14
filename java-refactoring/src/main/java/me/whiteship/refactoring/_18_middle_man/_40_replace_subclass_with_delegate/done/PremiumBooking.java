package me.whiteship.refactoring._18_middle_man._40_replace_subclass_with_delegate.done;

import java.time.LocalDateTime;

public class PremiumBooking extends Booking {

    private PremiumExtra extra;

    public PremiumBooking(Show show, LocalDateTime time, PremiumExtra extra) {
        super(show, time);
        this.extra = extra;
    }

    // talkback 체크 -> delegate 쪽으로 옮긴다.
    // (이 메서드는 중재자가 된다.) -> Booking 의 hasTalkback() 수정하여 아래 메서드는 필요 없어졌다.
//    @Override
//    public boolean hasTalkback() {
//        // delegate 로 옮겨진 메서드를 호출
//        return this.premiumDelegate.hasTalkback();
//    }

    // 이 메서드도 옮겨보자.
    // -> delegate 쪽으로 옮긴다.
//    @Override
//    public double basePrice() {
//        return Math.round(super.basePrice() + this.extra.getPremiumFee());
//    }

    // hasDinner 를 Booking에 올려주자.
    // 그후 delegate 로 옮겼다. 그에 따라 코드가 수정되었다.
//    public boolean hasDinner() {
//        return this.premiumDelegate != null && this.premiumDelegate.hasDinner();
//    }
}
