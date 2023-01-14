package me.whiteship.refactoring._06_mutable_data._21_replace_derived_variable_with_query;

/**
 * 파생 변수 -> 질의 함수
 * - 변경할 수 있는 데이터를 최대한 줄이도록 노력해야한다.
 * - 계산해서 알아낼 수 있는 변수는 제거 가능
 * - 계산에 필요한 데이터가 변하지 않는 값이라면, 계산의 결과에 해당하는 데이터 역시,
 * 불변 데이터이기 때문에 해당 변수는 그대로 유지할 수 있다.
 */
public class Discount {
    private double discount;

    private double baseTotal;

    public Discount(double baseTotal) {
        this.baseTotal = baseTotal;
    }

    public double getDiscountedTotal() {
        return this.baseTotal - this.discount;
    }

//    public double getDiscountedTotal() {
//        // 값 일치여부 체크
//        // 다르다면, 이 행에서 코드가 실패하게된다.
//        assert this.discountedTotal == calculatedDiscountedTotal();
//
//        return this.discountedTotal;
//    }
//
//    // 함수로 별도로 추출할 수 있다.
//    private double calculatedDiscountedTotal() {
//        return this.baseTotal - this.discount;
//    }

    public void setDiscount(double number) {
        this.discount = number;
//        this.discountedTotal = this.baseTotal - this.discount;
    }
}
