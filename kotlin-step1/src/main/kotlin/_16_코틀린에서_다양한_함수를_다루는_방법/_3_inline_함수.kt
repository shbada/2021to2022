package _16_코틀린에서_다양한_함수를_다루는_방법

// 함수가 호출되는 대신, 함수를 호출한 지점에 함수 본문을 그대로 복붙하고 싶은 경우
fun main() {
    3.add3(4) // int var10000 = $this$add$iv + other$iv
    // 왜 사용하냐?
    // 함수를 파라미터로 전달할대의 오버헤드를 줄일 수 있다.
    // inline 함수의 사용은 성능 측정과 함께 신중하게 사용되어야 한다.
}

inline fun Int.add3(other: Int): Int {
    return this + other
}