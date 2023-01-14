package me.whiteship.refactoring._08_shotgun_surgery._27_move_field.done;

import java.time.LocalDate;

public class CustomerContract {

    private LocalDate startDate;

    // 여기로 옮기기
    private double discountRate;

    public CustomerContract(LocalDate startDate, double discountRate) {
        this.startDate = startDate;
        this.discountRate = discountRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}
