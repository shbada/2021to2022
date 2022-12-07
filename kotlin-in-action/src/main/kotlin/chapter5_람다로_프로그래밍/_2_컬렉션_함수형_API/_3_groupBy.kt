package chapter5_람다로_프로그래밍._2_컬렉션_함수형_API

/*
gourpBy : 리스트를 여러 그룹으로 이뤄진 맵으로 변경
 */

fun main() {
    val people = listOf(Person("Alice", 31), Person("Bob", 29), Person("Carol", 31))
    println(people.groupBy { it.age }) // 같은 나이끼리 묶는다.
    // 각 그룹은 리스트다. 따라서 groupBy의 결과 타입은 Map<Int, List<Person>>이다. 이 맵을 mapKeys, mapValues 등을 사용하여 변경할 수 있다.

    val list = listOf("a", "ab", "b")
    println(list.groupBy(String::first)) // first 는 확장함수다. 멤버 참조로 접근할 수 있다.
}