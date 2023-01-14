package chapter2_코틀린기초._6_while과_for루프

/*
코틀린에는 자바의 for에 해당하는 요소가 없다.
이런 루프의 가장 흔한 용례인 초기값, 증가값, 최종값을 사용한 루프를 대신하기 위해 코틀린에서는 범위(range)를 사용한다.
범위는 기본저긍로 두 값으로 이뤄진 구간이다.
보통은 그 두 값은 정수 등의 숫자 타입의 값이며, .. 연산자로 시작 값과 끝 값을 연결해서 범위를 만든다.

val oneToTen = 1..10 (양 끝을 포함하는 구간 : 10까지 포함)

어떤 범위에 속한 값을 일정한 순서로 이터레이션하는 경우를 수열(progression)이라고 부른다.
 */

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

fun main() {
//    for (i in 1..100) {
//        println(fizzBuzz(i))
//    }
//
//    // 1 ~ 99
//    // 마지막 100을 포함하지 않기 위한
//    for (i in 1 until 100) { // = for (i in 1 .. 100-1)
//        println(fizzBuzz(i))
//    }

    // 100 downTo 1 : 역방향 수열을 만든다.. (기본 증가 값이 -1)
    // step 2 를 붙이면, 증가 값의 절댓값이 2로 바뀐다. (증가 값의 방향은 바뀌지 않고, 증가값은 실제로 -2)
    for (i in 100 downTo 1 step 2) {
        println(fizzBuzz(i))
    }
}