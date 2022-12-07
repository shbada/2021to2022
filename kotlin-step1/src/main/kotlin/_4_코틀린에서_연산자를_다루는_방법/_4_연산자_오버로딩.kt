package _4_코틀린에서_연산자를_다루는_방법

// Kotlin에서는 객체마다 연산자를 직접 정의할 수 있다.
data class Money(
    val amount: Long
) {
    operator fun plus(other: Money): Money {
        return Money(this.amount + other.amount)
    }
}

fun main() {
    val money1 = Money(1000L)
    val money2 = Money(2000L)

    println(money1.plus(money2)) // 가능하긴함
    println(money1 + money2) // + 연산자로 직접 정의 가능
}