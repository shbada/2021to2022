package _9_코틀린에서_클래스를_다루는_방법

fun main() {
    // JAVA 클래스를 코틀린에서 사용할때도 아래와 같은 방법으로 사용할 수 있다.
    val person = Person("KIM", 20)
    println(person.name) // getter
    person.age = 10 // setter
}