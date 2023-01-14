package me.whiteship.refactoring._18_middle_man._40_replace_subclass_with_delegate.done;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Booking {

    protected Show show;

    protected LocalDateTime time;

    protected PremiumDelegate premiumDelegate;

    public Booking(Show show, LocalDateTime time) {
        this.show = show;
        this.time = time;
    }

    // 팩토리 메서드
    // 생성자가 아닌 이유
    // 1) 함수명으로 자유롭게 표현할 수 있다.
    // 2) 리턴 타입이 더 자유롭다.
    public static Booking createBooking(Show show, LocalDateTime time) {
        return new Booking(show, time);
    }

    public static Booking createPremiumBooking(Show show, LocalDateTime time, PremiumExtra extra) {
//        PremiumBooking booking = new PremiumBooking(show, time, extra);
        // PremiumBooking 객체를 쓰지않아도 된다.
        Booking booking = createBooking(show, time); // 체이닝

        // 위임 장치 (가장 중요한 코드)
        booking.premiumDelegate = new PremiumDelegate(booking, extra);
        return booking;
    }

    public boolean hasTalkback() {
        return (this.premiumDelegate != null) ? this.premiumDelegate.hasTalkback() : this.show.hasOwnProperty("talkback") && !this.isPeakDay();
    }

    protected boolean isPeakDay() {
        DayOfWeek dayOfWeek = this.time.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public double basePrice() {
        double result = this.show.getPrice();
        if (this.isPeakDay()) result += Math.round(result * 0.15);
        return (this.premiumDelegate != null) ? this.premiumDelegate.extendBasePrice(result) : result;
    }

    // delegate 로 다시 옮기자.
    public boolean hasDinner() {
        return this.premiumDelegate != null && this.premiumDelegate.hasDinner();
    }

}
