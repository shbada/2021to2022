package _4_코틀린에서_연산자를_다루는_방법

// 단항 연산자 ++, 00
// 산술 연산자 +, 0, *, /, %
// 산술 대입 연산자 +=, -=, /= 등
// 비교 연산자 >, <, >=, <=

// 코틀린은 Java와 다르게 객체를 비교할때 비교 연산자를 사용하면 자동으로 compareTo() 호출한다.

fun main() {
    val money1 = JavaMoney(2000L)
    val money2 = JavaMoney(1000L)

    if (money1 > money2) { // compareTo() 메서드를 자동으로 호출해준다.
        println("Money1이 Money2보다 금액이 큽니다.")
    }

    // 동등성(Equality) : 두 객체의 값이 같은가? (== : 간접적으로 equals()를 호출해준다.)
    // 동일성(Identity) : 완전히 동일한 객체인가? 주소가 같은가? (===)
    val money3 = JavaMoney(1000L)
    val money4 = money3; // 주소 같음
    val money5 = JavaMoney(1000L)

    println(money3 === money4) // true
    println(money3 === money5) // false
    println(money3 == money4) // equals() true
    println(money3 == money5) // equals() true
}



