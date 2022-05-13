package _기본문법._4_클래스_프로퍼티

import java.util.*

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }
}

fun main(array: Array<String>) {
    // 프로퍼티명을 사용해도 코틀린이 자동으로 게터를 호출해준다.
    println(createRandomRectangle().isSquare)
}

fun createRandomRectangle(): Rectangle {
    val random = Random()
    // 코를린은 자바와 다르게 new 연산자를 쓰지 않는다.
    return Rectangle(random.nextInt(), random.nextInt())
}