package _12_코틀린에서_object키워드를_다루는_방법

// 특정 인터페이스나 클래스를 상속받은 구현체를 일회성으로 사용

fun main() {
    moveSomething(object : Movable { // object : 타입이름 으로 익명클래스 표현
        override fun move() {
            println("움직인다")
        }

        override fun fly() {
            println("난다")
        }

    })
}
private fun moveSomething(movable: Movable) {
    movable.move()
    movable.fly()
}