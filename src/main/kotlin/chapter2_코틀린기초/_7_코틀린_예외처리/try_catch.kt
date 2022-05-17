package chapter2_코틀린기초._7_코틀린_예외처리

import java.io.BufferedReader
import java.io.StringReader

fun main() {
    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)
}

fun readNumber(reader: BufferedReader): Int? {
    /*
    자바에서는 메서드 선언 뒤에 throws IOException 을 붙여야한다.
    IOException이 체크 예외기 때문이다.
    코틀린은 체크예외, 언체크예외를 구별하지 않는다.
    코틀린에서는 함수가 던지는 예외를 지정하지 않고 발생한 예외를 잡아내도 되고,
    잡아내지않아도 된다.

    참고로 코틀린에서는 try~with~resource 와 같은 특별한 문법은 제공하지 않는다.
    하지만 라이브러리 함수로 같은 기능을 구현할 수 있다. (추후에 배운다.)
     */
    try {
        // 함수가 던질 수 있는 예외를 명시할 필요가 없다.
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        // 예외 타입을 :의 오른쪽에 쓴다.
        return null
    } finally {
        // finally 는 자바와 똑같이 작동한다.
        reader.close()
    }
}