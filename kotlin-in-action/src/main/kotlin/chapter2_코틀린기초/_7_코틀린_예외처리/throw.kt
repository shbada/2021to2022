package chapter2_코틀린기초._7_코틀린_예외처리

/*
코틀린의 예외 처리는 자바나 다른 언어의 예외 처리와 비슷하다.
 */

fun main() {
    val i = 50
    if (i !in 0.. 100) {
        // new 연산자를 사용하지 않아도 된다.
        throw IllegalArgumentException("exception : $i")
    }

    val percentage = exam(101)
    println(percentage)

}

fun exam(i: Int) {
    val percentage =
        if (i in 0.. 100) {
            101
        } else {
            // throw 는 식이다. 그러므로 다른 식에 포함될 수 있다.
            throw IllegalArgumentException("exception : $i")
        }
}