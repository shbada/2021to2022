package _19_코틀린의_이모저모


// 구조 분해 : 복합적인 값을 분해하여 여러 변수를 한번에 초기화하는 것
// map.entries 등의 문법도 모두 구조분해다.
fun main() {
    val person = Person("KIM", 100)
    // 순서가 있으므로 바꿔서 넣으면 안된다.
    val (name, age) = person // name, age를 한번에 초기화 가능하다

    /*
    data class 는 자신이 가지고있는 프로퍼티에 대해서 componentN 을 만든다.

    val name = person.component1() // name
    val age = person.component2() //  age
     */

    // data class 가 아니라면?
    val person2 = Person2("KIM", 100)
    val (name2, age2) = person2
}

class Person2 (
    val name2: String,
    val age2: Int
) {
    // operator 키워드 추가
    // componentN :연산자의 속성을 가지고 있기 때문에, 연산자 오버로딩을 하는것처럼 간주되어야한다.
    operator fun component1(): String {
        return this.name2
    }

    operator fun component2(): Int {
        return this.age2
    }
}

data class Person (
    val name: String,
    val age: Int
)