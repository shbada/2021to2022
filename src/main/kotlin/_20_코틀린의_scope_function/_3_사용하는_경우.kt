package _20_코틀린의_scope_function

import _12_코틀린에서_object키워드를_다루는_방법.Person

fun main() {
    /** let */
    // 하나 이상의 함수를 call chain 결과로 호출할때
    val strings = listOf("APPLE", "CAR")
    strings.map { it.length }
        .filter { it > 3 } // list int
        .let(::println) // list int 자체를 print
//        .let(lengths -> println(lengths)) // list int 자체를 print

    // non-null 값에 대해서만 code block을 실행시킬때
//    val length = str?.let {
//        println(it.uppercase())
//        it.length
//    }

    // 일회성으로 제한된 영역에 지역변수를 만들때
    val numbers = listOf("A", "B")
    val item = numbers.first()
        .let { firstItem -> if (firstItem.length >= 5) firstItem else "aa" }

    /** run */
    // 객체 초기화와 반환 값의 계산을 동시에 해야할때
    // 객체를 만들어 DB에 바로 저장하고 그 인스턴스를 활용할때
    // 잘 사용하지 않음 - personRepository.save(Person("K", 100)) 이게 더 익숙하다.
//    val person = Person("K", 100).run(personRepository::save)

    /** also */
// 객체를 수정하는 로직이 call chain 중간에 필요할때
    mutableListOf("A", "B", "C")
        .also{println("aa")}
        .add("four")

    // 위 코드와 완전 동일하다.
    val numbers23 = mutableListOf("A", "B", "C")
    println("aa")
    numbers23.add("four")

    /** with */
    // 특정 객체를 다른 객체로 변환해야 하는데, 모듈 간의 의존성에 의해 정적 팩토리 혹은 toClass 함수를 만들기 어려울때
//    return with(person) {
//        PersonDto( // this 생략 가능 (this가 Person임) - 코드가 간결해짐
//            name = name,
//            age = age
//        )
//    }
}

/** apply */
// 객체 그 자체가 반환된다.
// 객체 설정을 할때에 객체를 수정하는 로직이 call chain 중간에 필요할때
//fun createPerson(
//    name: String,
//    age: Int,
//    hobby: String,
//): Person {
//    return Person(
//        name = name,
//        age = age
//    ).apply { // 생성자에 있지 않을 수 있을때 apply로 추가로 작성해줄 수 있다.
//        this.hobby = hobby
//    }
//}

// call chain 중간에 값을 수정하는 경우
//person.apply { this.growOld() }.let { println(it) }

