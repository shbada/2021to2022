package _8_코틀린에서_함수를_다루는_방법

// 두 정수를 받아 더 큰 정수를 반환하는 예제
fun main() {

}

// public 생략 가능
// fun : 함수를 의미하는 키워드
// max : 함수 이름
// 함수의 매개변수 a, b
// 반환타입
fun max(a: Int, b: Int): Int {
    // 하나의 expression
    return if (a > b) {
        return a
    } else {
        return b
    }
}

// = 으로 변경
fun max2(a: Int, b: Int): Int =
    // 하나의 expression
    if (a > b) {
        a
    } else {
        b
    }

// block {} 생략 가능
// 타입 생략 가능
// = 인 경우는 반환 타입 추론이 가능하다
// block {}을 사용하는 경우에는 반환타입이 Unit이 아니면 반환 타입을 명시해줘야하낟.
fun max3(a: Int, b: Int) = if (a > b) a else b
