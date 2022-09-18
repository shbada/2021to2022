package me.whiteship.refactoring._08_shotgun_surgery._27_move_field.done;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 리팩토링 27. 필드 옮기기
 * - 필드 옮기기 단서
 * 1) 어떤 데이터를 항상 어떤 레코드와 함께 전달하는 경우
 * 2) 어떤 레코드를 변경할때 다른 레코드에 있는 필드를 변경해야하는 경우
 * 3) 여러 레코드에 동일한 필드르 수정해야하는 경우
 * (레코드는 클래스 또는 객체)
 */
public class Customer {

    private String name;

    // CustomerContract 로 이동
//    private double discountRate;

    private CustomerContract contract;

    public Customer(String name, double discountRate) {
        this.name = name;
//        this.discountRate = discountRate;
        this.contract = new CustomerContract(dateToday(), discountRate);
    }

    public double getDiscountRate() {
        return this.contract.getDiscountRate();
    }

    /* 아래의 행위들이 CustomerContract와 더 연관있지 않을까? 라는 고민 */
    // Set 메서드 생성
    public void setDiscountRate(double discountRate) {
        this.contract.setDiscountRate(discountRate);
    }

    public void becomePreferred() {
        this.setDiscountRate(this.getDiscountRate() + 0.03);
//        this.discountRate += 0.03;
        // 다른 작업들
    }

    public double applyDiscount(double amount) {
        BigDecimal value = BigDecimal.valueOf(amount);
        return value.subtract(value.multiply(BigDecimal.valueOf(this.getDiscountRate()))).doubleValue();
    }

    private LocalDate dateToday() {
        return LocalDate.now();
    }
}
