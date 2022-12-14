package me.whiteship.refactoring._04_long_parameter_list._15_remove_flag_argument;

import java.time.LocalDate;

/**
 * 리팩토링 15. 플래그 인스 주게허가ㅣ (Remove Flag Argument)
 * - 플래그는 보통 함수에 매개변수로 전달해서, 함수 내부의 로직을 분기하는데 사용한다.
 * - 플래그를 사용한 함수는 차이를 파악하기 어렵다.
 *  > bookConcert(customer, false), bookConcert(customer, true)
 *  > bookConcert(customer), premiumBookConcert(customer) // 이게 더 직관적
 * - 조건문 분해하기 (Decompost Condition)를 활용할 수 있다.
 *
 * src/test/java/me/whiteship/refactoring/_04_long_parameter_list/_15_remove_flag_argument
 */
public class Shipment {

    public LocalDate deliveryDate(Order order, boolean isRush) {
        if (isRush) {
            int deliveryTime = switch (order.getDeliveryState()) {
                case "WA", "CA", "OR" -> 1;
                case "TX", "NY", "FL" -> 2;
                default -> 3;
            };
            return order.getPlacedOn().plusDays(deliveryTime);
        } else {
            int deliveryTime = switch (order.getDeliveryState()) {
                case "WA", "CA" -> 2;
                case "OR", "TX", "NY" -> 3;
                default -> 4;
            };
            return order.getPlacedOn().plusDays(deliveryTime);
        }
    }
}
