package chapter5_람다로_프로그래밍._3_지연계산_컬렉션연산

/*
filter, map은 리스트를 반환한다.
이는 이 연쇄 호출이 리스트를 2개 만든다는 뜻이다. 한 리스트는 filter 결과를 담고, 다른 하나는 map 결과를 담는다.
각 연산이 컬렉션을 직접 사용하는 대신, 시퀀스를 사용하게 만들자.
 */

data class Person(val name: String, val age: Int)

fun main() {
    val people = listOf(
        Person("Alice", 29),
        Person("Bob", 31)
    )

    val filter = people.map(Person::name).filter{ it.startsWith("김") }

    /** 성능이 더 좋다. */
    val toList = people.asSequence() // 원본 컬렉션을 시퀀스로 변환한다.
        .map(Person::name)
        .filter { it.startsWith("김") }
        .toList() //결과 시퀀스를 다시 리스트로 변환한다.

    /*
       코틀린의 지연 계산 시퀀스는 Sequence 인터페이스에서 시작한다.
       이 인터페이스는 단지 한번에 하나씩 열거될 수 있는 원소의 시퀀스를 표현할 뿐이다.
       Sequence 안에는 iterator라는 단 하나의 메서드가 있다.
       그 메서드를 통해 시퀀스로부터 원소 값을 얻을 수 있다.

       시퀀스의 원소는 필요할때 계산된다.
       따라서 중간 처리 결과를 저장하지 않고도 연산을 연쇄적으로 적용해서 효율적으로 계산을 수행할 수 있다.

       asSequence 확장 함수를 호출하면 어떤 컬렉션이든 시퀀스로 바꿀수있다.
       시퀀스를 리스트로 만들때는 toList를 사용한다.

       시퀀스를 다시 컬렉션으로 되돌려야하는 이유
       시퀀스의 원소를 차례로 이터레이션해야 한다면 시퀀스를 직접 써도 되지만,
       시퀀스 원소를 인덱스를 사용해 접근하는 등의 다른 API 메서드가 필요하다면 시퀀스를 리스트로 변환해야한다.
     */
}