package _3_코틀린에서_Type을_다루는_방법

fun main() {
    // 기본값을 보고 타입 추론한다.
    val number1 = 3 // Int
    val number2 = 3L // Long
    val number3 = 3.0f // Float
    val number4 = 3.2 // Double

    // 코틀린은 타입 변환이 명시적이다.
    val number5 = 4
//    val number6 :Long = number5 // Type mismatch

    // 코틀린에서는 암시적 타입 변경이 불가능하다.
    // toLong(), toString() 등과 같은 toXX() 메서드를 사용해야한다.

    val number6 = 3
    val number7: Long = number6.toLong() // 명시적 선언
    print(number6 + number7)

    // 변수가 nullable 이라면 적절한 처리가 필요하다.
    val number8: Int? = 3
    val number9: Long = number8?.toLong() ?: 0L // nullable 일 경우 Elvis
}