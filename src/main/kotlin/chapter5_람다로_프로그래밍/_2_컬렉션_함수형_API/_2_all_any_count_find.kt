package chapter5_람다로_프로그래밍._2_컬렉션_함수형_API

/*
all, any : 모든 원소가 어떤 조건을 만족하는지 판단
count : 조건을 만족하는 개수를 반환
find : 조건을 만족하는 첫번째 원소 반환
 */

// all, any
val canBeInClub27 = { p: Person -> p.age <= 27 }

fun main() {
    val people = listOf(Person("Alice", 27), Person("Bob", 31))
    println(people.all(canBeInClub27)) // 모든 원소가 이 술어를 만족하는가?

    println(people.any(canBeInClub27))  //이 술어를 만족하는 원소가 하나라도 있는가?

    val list = listOf(1, 2, 3);
    println(!list.all { it == 3 } == list.any { it != 3 }) // 둘의 결과는 같다.
    println(!list.all { it == 3 }) // any를 사용하는게 더 낫다. (결과는 같으므로)
    println(list.any { it != 3 })

    // count
    println(people.count(canBeInClub27))

    // 술어를 만족하는 원소를 하나 찾고 싶다.
    // 가장 먼저 조건을 만족한다고 확인된 원소를 반환한다.
    // 만족하는 원소가 전혀 없는 경우 null을 반환한다. find는 firstOrNull과 같다.
    println(people.find(canBeInClub27))
    println(people.firstOrNull(canBeInClub27))
}