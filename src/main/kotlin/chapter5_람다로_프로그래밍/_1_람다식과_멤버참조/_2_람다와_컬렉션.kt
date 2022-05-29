package chapter5_람다로_프로그래밍._1_람다식과_멤버참조

/*
코드에서 중복을 제거하는 것은 프로그래밍 스타일을 개선하는 중요한 방법이다.
 */

// 사람의 이름과 나이를 저장하는 Person 클래스를 사용하자.
data class Person(val name: String, val age: Int)

/*
사람들로 이뤄진 리스트가 있고, 그중에 가장 연장자를 찾고싶다.
람다를 사용해본 경험이 없는 개발자라면 루프를 써서 직접 검색을 구현할 것이다.
 */

// 컬렉션을 직접 검색하기
fun findTheOldest(people: List<Person>) {
    var maxAge = 0 // 가장 많은 나이를 저장한다.
    var theOldest: Person? = null // 가장 연장자인 사람을 저장한다.

    for (person in people) {
        if (person.age > maxAge) { // 현재까지 발견한 최연장자보다 더 나이가 많은 사람 찾으면 최댓값 바꾼다.
            maxAge = person.age
            theOldest = person
        }
    }

    println(theOldest)
}

fun main(args: Array<String>) {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))

    // 컬렉션을 직접 검색
    findTheOldest(people) // Person(name=Bob, age=31)

    // 람다를 사용해 컬렉션 검색
    // // Person(name=Bob, age=31)
    println(people.maxByOrNull { it.age }) // 나이 프로퍼티를 비교해서 값이 가장 큰 원소 찾기

    // 멤버 참조를 사용해 컬렉션 검색
    println(people.maxByOrNull(Person::age)) // Person(name=Bob, age=31)

    /* 람다식 사용 */
    // 파라미터 타입 명시
    println(people.maxByOrNull ({ p: Person -> p.age }))

    // 함수 호출 시, 맨 뒤에 있는 인자가 람다식이라면 그 람다를 괄호 밖으로 빼낼 수 있다.
    println(people.maxByOrNull { p: Person -> p.age })

    // 파라미터 타입을 생략(컴파일러가 추론)
    println(people.maxByOrNull { p -> p.age })

    // 람다의 파라미터가 하나뿐이고, 그 타입을 컴파일러가 추론할 수 있는 경우 it 사용 가능
    // it : 자동 생성된 파라미터 이름 (람다 이름을 지정하지 않았을 경우에만 디폴트 파라미터 이름 it 사용)
    println(people.maxByOrNull { it.age }) // 나이 프로퍼티를 비교해서 값이 가장 큰 원소 찾기

    // 람다를 변수에 저장할 때는 파라미터의 타입을 추론할 문맥이 존재하지 않아서, 파라미터 타입 명시해야한다.
    val getAge = { p: Person -> p.age }
    println(people.maxByOrNull(getAge))

    // 본문이 여러 줄로 이뤄진 경우 본문의 맨 마지막에 있는 식이 람다의 결과값이 된다.
    val sum = { x: Int, y: Int ->
        println("Computing the sum of $x and $y...")
        x + y
    }
    println(sum(1, 2)) // 3

    // 이름 붙인 인자를 사용하여 람다 넘기기
    val personList = listOf(Person("이몽룡", 29), Person("성춘향", 31))
    val names = personList.joinToString(separator = " ", transform = { p: Person -> p.name})
    println(names) // 이몽룡 성춘향

    // 람다를 괄호 밖에 전달하기
    val names2 = personList.joinToString(" ") { p: Person -> p.name}
    println(names2) // 이몽룡 성춘향
}