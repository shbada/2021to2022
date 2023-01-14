package _1_코틀린에서_변수를_다루는_방법

// Primitive Type
fun main() {
    // nullable
    var number2 = 10L
//    number2 = null // 기본적으로 모든 변수는 null이 들어갈 수 없게끔 설계되었다.

    var number3: Long? = 10L
    number3 = null // Long? 타입?을 사용해야 null 이 들어갈 수 있다.
}