package me.whiteship.refactoring._24_comments._43_introduce_assertion.done;

public class Customer {

    private Double discountRate;

    // amount 가 음수가 아니라고 가정하고있다.
    public double applyDiscount(double amount) {
        return (this.discountRate != null) ? amount - (this.discountRate * amount) : amount;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        // 최초에 값이 셋팅되는 위치에 assert를 넣는게 맞다고 생각한다.
        assert discountRate != null && discountRate > 0;
        this.discountRate = discountRate;
    }
}
