package _10_코틀린에서_상속_다루는_방법

/*
open class Base (상위)

class Derived (하위)
 */

fun main() {
    /*
    Base Class
    0  --> 왜 0일까?
    Derived Class
     */
    Derived(300)
}

open class Base(
    // 상위 클래스를 설계할때 생성자 또는 초기화 블록에 사용되는 프로퍼티에는 open을 피해야한다.
    open val number: Int = 100
) {
    init {
        println("Base Class")
        println(number) // 상위클래스 생성자가 생성되는 동안, 하위 클래스의 number 는 아직 초기화가 안되었다.
    }
}

class Derived(
    override val number: Int
) : Base(number) {
    init {
        println("Derived Class")
    }
}