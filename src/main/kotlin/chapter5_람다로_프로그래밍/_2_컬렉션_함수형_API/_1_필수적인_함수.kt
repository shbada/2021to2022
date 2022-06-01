package chapter5_람다로_프로그래밍._2_컬렉션_함수형_API

import java.util.*

/*
filter
- 결과 : 입력 컬렉션의 원소 중에서 주어진 술어(참/거짓을 반환하는 함수 : predicate) 를 만족하는 원소만으로 이뤄진 새로운 컬렉션
 */

data class Person(val name: String, val age: Int)

fun main() {
    val list = listOf(1, 2, 3, 4)
    println(list.filter { it % 2 == 0 }) // 짝수만 남는다.

    val people = listOf(Person("Alice", 27), Person("Bob", 31))
    println(people.filter { it.age > 30 }) // 30살 이상인 사람만 출력

    // map
    val list2 = listOf(1, 2, 3, 4)
    println(list2.map { it * it }) // 제곱이 모인 리스트로 바꾼다.

    val people2 = listOf(Person("Alice", 27), Person("Bob", 31))
    println(people2.map { it.name }) // 사람의 이름의 리스트로 변환한다.
    println(people2.map(Person::name)) // 멤버 참조 사용

    // 30살 이상인 사람의 이름을 출력하기
    println(people.filter { it.age > 30 }.map { Person::name })

    // 목록에 있는 사람들의 나이의 최댓값을 구하고, 그 최댓값과 같은 모든 사람을 반환
    // 최댓값을 구하는 작업을 계속해서 반복한다.
    println(people.filter {it.age == people.maxByOrNull(Person::age) !!.age})
    // 한번만 반복하도록 하자.
    val maxAge = people.maxByOrNull(Person::age) !!.age;
    println(people.filter { it.age == maxAge })

    // 맵의 경우 키와 값을 처리하는 함수가 따로 존재한다.
    val numbers = mapOf(0 to "zero", 1 to "one")
    println(numbers.mapValues { it.value.uppercase(Locale.getDefault()) })
}

/*
map
- 주어진 람다를 컬렉션의 각 원소에 적용한 결과를 모아서 새 컬렉션을 만든다.
- 결과 : 원본 리스트와 원소의 개수는 같지만, 각 원소는 주어진 함수에 따라 변환된 새로운 컬렉션이다.
 */
