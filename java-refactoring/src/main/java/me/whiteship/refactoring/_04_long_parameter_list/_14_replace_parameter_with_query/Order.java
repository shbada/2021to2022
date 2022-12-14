package me.whiteship.refactoring._04_long_parameter_list._14_replace_parameter_with_query;

/**
 * 리팩토링 14. 매개변수를 질의 함수로 바꾸기
 * - 함수의 매개변수 목록은 함수의 다양성을 대변하며, 짧을 수록 이해하기 좋다.
 * - 어떤 한 매개변수를 다른 매개변수를 통해 알아냈다면 "중복 매개변수"라 생각할 수 있다.
 * - 매개변수에 값을 전달하는 것은 "함수를 호출하는 쪽"의 책임이다.
 * 가능하면 함수를 호출하는 쪽의 책임을 줄이고 함수 내부에서 책임지도록 노력한다.
 * - "임시 변수를 질의 함수로 바꾸기"와 "함수 선언 변경하기"를 통해 이 리팩토링을 적용한다.
 *
 * src/test/java/me/whiteship/refactoring/_04_long_parameter_list/_14_replace_parameter_with_query
 */
public class Order {

    private int quantity;

    private double itemPrice;

    public Order(int quantity, double itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public double finalPrice() {
        double basePrice = this.quantity * this.itemPrice;
        int discountLevel = this.quantity > 100 ? 2 : 1;
        return this.discountedPrice(basePrice, discountLevel);
    }

    private double discountedPrice(double basePrice, int discountLevel) {
        return discountLevel == 2 ? basePrice * 0.90 : basePrice * 0.95;
    }
}
