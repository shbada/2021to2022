package com.spring.junit.calculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.when;

@SpringBootTest /* 모든 빈이 등록되므로 아래 @Import 주석 가능 */
// @Import({MarketApi.class, DollarCalculator.class}) /* 주입에 필요하므로 사용 */
class DollarCalculatorTest {

    /* 스프링은 빈으로 처리하기 때문에 MockBean */
    @MockBean
    private MarketApi marketApi;

    @Autowired
    private DollarCalculator dollarCalculator;

    @Autowired
    private Calculator calculator;

    @Test
    public void dollarCalculatorTest() {
        when(marketApi.connect()).thenReturn(3000);
        dollarCalculator.init();

        int sum = dollarCalculator.sum(10, 10);
        int minus = dollarCalculator.minus(10, 10);

        Assertions.assertEquals(60000, sum);
        Assertions.assertEquals(0, minus);
    }

    @Test
    public void calculatorTest() {
        when(marketApi.connect()).thenReturn(3000);

        int sum = calculator.sum(10, 10);
        int minus = calculator.minus(10, 10);

        Assertions.assertEquals(60000, sum);
        Assertions.assertEquals(0, minus);
    }
}