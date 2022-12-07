package _15_코틀린에서_배열과_컬렉션을_다루는_방법

import java.util.*

// 컬렉션이 불변인지, 가변인지를 설정해야한다.
// 1) 가변 컬렉션 (Mutable) :  컬렉션에 element를 추가, 삭제할 수 있다.
// 2) 불변 컬렉션 : 컬렉션에 element를 추가, 삭제할 수 없다.
// Collections.unmodifiableList() // 불변
// 불변리스트에 1번째 원소에 접근해서 그 안의 필드(예시 price)를 바꿀수는 있다.

fun main() {
    // List
    val numbers = listOf(100, 200) // 불변 리스트
    val emptyList = emptyList<Int>() // 빈 리스트는 타입을 명시해야한다.

    printNumbers(emptyList) // Int 로 타입을 추론할 수 있다. 이런 경우에는 빈 리스트 생성시에도 타입 명시를 하지않아도 된다.

    println(numbers[0])
    for (number in numbers) {
        println(number)
    }

    for ((idx, value) in numbers.withIndex()) {
        println("$idx $value")
    }

    // 가변 리스트
    val numbers2 = mutableListOf(100, 200)
    numbers2.add(300)

}

private fun printNumbers(numbers: List<Int>) {

}

