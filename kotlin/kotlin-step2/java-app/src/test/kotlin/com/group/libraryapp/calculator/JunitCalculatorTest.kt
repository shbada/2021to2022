package com.group.libraryapp.calculator

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.*

class JunitCalculatorTest {
    @Test
    fun addTest() {
        /* given-when-then 패턴 */

        // given (테스트 준비)
        val calculator = Calculator(5)

        // when (테스트 기능 호출)
        calculator.add(3)

        // then (호츨 결과 검증)
        assertThat(calculator.number).isEqualTo(8);
    }

    @Test
    fun minusTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.minus(3)

        // then
        assertThat(calculator.number).isEqualTo(2);
    }

    @Test
    fun multiplyTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.multiply(3)

        // then
        assertThat(calculator.number).isEqualTo(15);
    }

    @Test
    fun divideTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.divide(2)

        // then
        assertThat(calculator.number).isEqualTo(2);
    }

    @Test
    fun divideExceptionTest() {
        // given
        val calculator = Calculator(5)

        // when & then
        val message = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.message

        assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")
    }
}