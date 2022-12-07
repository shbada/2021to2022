package _9_코틀린에서_클래스를_다루는_방법

//class Person constructor(name: String, age: Int) { // 생성자 작성
class Person (name: String, age: Int) { // 기본 생성자 작성 (constructor 키워드는 생략 가능)
    // Kotlin은 필드를 만들면 getter, setter을 자동으로 만들어준다.

    // 불변 필드
    val name = name
    // 변경가능 필드
    var age = age
}

// 위 클래스를 아래처럼도 가능하다.
class Person2 (val name: String, var age: Int) { // 생성자 작성 (constructor 키워드는 생략 가능)
    // Kotlin은 필드를 만들면 getter, setter을 자동으로 만들어준다.
}

// 중괄호도 생략 가능
class Person3 (
    val name: String,
    var age: Int)
