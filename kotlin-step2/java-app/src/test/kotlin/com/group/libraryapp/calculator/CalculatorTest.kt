package com.group.libraryapp.calculator

/**
 * 관례)
 * - 동일 패키지
 * - 클래스명 + Test
 *
 * 수동 테스트의 단점
 * 1) 테스트 메서드를 개별적으로 수행하기 어렵다.
 * 2) 테스트가 실패한 경우 어떤 경우 실패했는지 알기 어렵다. (기댓값/실제결과값 비교가 어렵다.)
 * 3) 직접 구현해야할 부분이 너무 많다.
 * 4) 공통 로직(중복)을 처리하기가 어렵다.
 * -> Junit5 를 사용해야한다.
 */
fun main() {
    val calculatorTest = CalculatorTest()

    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.divideTest()
}

class CalculatorTest {
    fun addTest() {
        /* given-when-then 패턴 */

        // given (테스트 준비)
        val calculator = Calculator(5)

        // when (테스트 기능 호출)
        calculator.add(3)

        // then (호츨 결과 검증)
        // 결과 확인?
        // 1) Calculator를 data class 로 해주면 프로퍼티로 접근 가능
//        val expedcted = Calculator(8)
//        if (calculator != expedcted) {
//            throw IllegalStateException()
//        }

        // 2) Calculator의 number 을 public으로 변경해준다.
        // -> 거부감이 든다. 외부에서 set 해버릴 수도 있기 때문이다.
        // -> 이럴땐 백킹 프로퍼티를 사용한다. private 을 다시 붙히고, _number 로 변경하고 number 커스텀 get()을 사용한다.
        if (calculator.number != 8) {
            throw IllegalStateException()
        }
    }

    fun minusTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.minus(3)

        // then
        if (calculator.number != 2) {
            throw IllegalStateException()
        }
    }

    fun multiplyTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.multiply(3)

        // then
        if (calculator.number != 15) {
            throw IllegalStateException()
        }
    }

    fun divideTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.divide(2)

        // then
        if (calculator.number != 2) {
            throw IllegalStateException()
        }
    }

    fun divideExceptionTest() {
        // given
        val calculator = Calculator(5)

        // when
        try {
            calculator.divide(0)
        } catch (e: IllegalArgumentException) {
            if (e.message != "0으로 나눌 수 없습니다.") {
                throw IllegalStateException("메시지가 다릅니다.")
            }
            // 테스트 성공
            return
        } catch(e: Exception) {
            throw IllegalStateException()
        }

        throw IllegalStateException("기대하는 예외가 발생하지 않았습니다.")
    }
}