package _8_코틀린에서_함수를_다루는_방법

fun main() {
    repeat("Hello World")
    repeat("Hello World", 5, false)
    repeat("Hello World", 7)
    repeat("Hello World", 1, true)
}

// 주어진 문자열을 N번 출력하는 예제
// default parameter : 밖에서 파라미터를 넣어주지 않으면 기본값을 사용한다
// 코틀린도 자바와 동일하게 오버로드 기능이 존재한다.
fun repeat(
    str: String,
    num: Int = 3, // default 3
    useNewLine: Boolean = true // default true
) {
    for (i in 1..num) {
        if (useNewLine) {
            println(str)
        } else {
            print(str)
        }
    }
}