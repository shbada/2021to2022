package _9_코틀린에서_클래스를_다루는_방법

import java.lang.IllegalArgumentException

fun main() {
    // JAVA 클래스를 코틀린에서 사용할때도 아래와 같은 방법으로 사용할 수 있다.
    val person = Person("KIM", 20)
    println(person.name) // getter
    person.age = 10 // setter
}

class Person5 (
    // 기본생성자 (=주생성자)는 반드시 존재해야한다.
    val name: String,
    var age: Int) {
    // 초기화 (생성자가 호출되는 시점에 수행된다.)
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }

        println("초기화 블록")
    }

    // 부 생성자 생성 (필수가 아니다.)
    constructor(name: String): this(name, 1) { // 위의 기본 생성자를 호출
        println("첫번째 부 생성자") // body add 가능
    }

    constructor(): this("홍길동") // 이렇게도 가능하다.
}

// 기본적으로 생성자가 자등으로 만들어져있다. Student()
class Student

class Person6 (
    // 기본생성자 (=주생성자)는 반드시 존재해야한다.
    // default 값을 설정해서 부생성자를 제거해보자.
    val name: String = "KIM",
    var age: Int = 10) {
    // 초기화 (생성자가 호출되는 시점에 수행된다.)
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }

        println("초기화 블록")
    }
}