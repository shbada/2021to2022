package _9_코틀린에서_클래스를_다루는_방법

import java.lang.IllegalArgumentException

fun main() {
    // JAVA 클래스를 코틀린에서 사용할때도 아래와 같은 방법으로 사용할 수 있다.
    val person = Person("KIM", 20)
    println(person.name) // getter
    person.age = 10 // setter

    val person7 = Person7()
    println(person7.isAdult)
}

class Person7 (
    // 기본생성자 (=주생성자)는 반드시 존재해야한다.
    name: String = "KIM",
    var age: Int = 10) {
    // 초기화 (생성자가 호출되는 시점에 수행된다.)
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }

        println("초기화 블록")
    }

    val name = name // 주 생성자에서 받은 name을 담는다.
        get() = field.uppercase() // field 키워드
    // field가 무엇인가? (backing field)
    // name을 불렀을때 get이 불려지는데, name이 호출되는데, person.name 이라고하면 getter가 호출된다.
    // name.uppercase() -> name.getter를 계속 부르기 때문에 field 키워드를 사용해야한다. (무한루프 방지)

    // 함수
//    fun isAdult(): Boolean {
//        return this.age >= 20
//    }

    // 함수 대신 프로퍼티로도 가능하다. (custom getter)
    // 이 사람이 성인인가?의 속성을 확인한다면 함수가 아닌 custom getter 로 구현하는걸 추천한다.
    val isAdult: Boolean
        get() = this.age >= 20

    val isAdult2: Boolean
        get() {
            return this.age >= 20
        }
}

class Person8 (
    // 기본생성자 (=주생성자)는 반드시 존재해야한다.
    val name: String = "KIM",
    var age: Int = 10) {
    // 초기화 (생성자가 호출되는 시점에 수행된다.)
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }

        println("초기화 블록")
    }

    // 함수
    fun getUppercaseName(): String {
        return this.name.uppercase()
    }

    // 프로퍼티
    val uppercaseName2: String
        get() = this.name.uppercase()
}

class Person9 (
    name: String = "KIM",
    var age: Int = 10) {
    // 초기화 (생성자가 호출되는 시점에 수행된다.)
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }

        println("초기화 블록")
    }

    var name = name
        set(value) { // setter
            field = value.uppercase()
        }
}