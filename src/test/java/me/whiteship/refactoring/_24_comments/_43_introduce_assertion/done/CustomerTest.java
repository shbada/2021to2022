package me.whiteship.refactoring._24_comments._43_introduce_assertion.done;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void getDiscountRate() {
        Customer customer = new Customer();
        customer.setDiscountRate(-1d); // assert 문으로 에러로 체크된다. (JVM 옵션에 -ea를 넣어야 assert를 체크한다.)
    }
}