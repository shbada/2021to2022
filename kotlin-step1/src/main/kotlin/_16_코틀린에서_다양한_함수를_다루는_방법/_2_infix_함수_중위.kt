package _16_코틀린에서_다양한_함수를_다루는_방법

// downTo, step 도 함수다. (중위 호출 함수)
// 변수.함수이름(argument) , 변수 함수이름 argument (변수, argument가 각각 1개씩 있을때)
fun Int.add(other: Int): Int {
    return this + other
}

infix fun Int.add2(other: Int): Int {
    return this + other
}

fun main() {
    3.add(4)
    3.add2(4)
    3 add2 4
}