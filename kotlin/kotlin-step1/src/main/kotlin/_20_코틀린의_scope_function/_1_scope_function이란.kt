package _20_코틀린의_scope_function

import _12_코틀린에서_object키워드를_다루는_방법.Person

/*
scope : 영역
function : 함수

scope function : 일시적인 영역을 형성하는 함수

람다를 사용해서 일시적인 영역을 만들고, 코드를 더 간결하게 만들거나, method chaining 을 활용하는 함수
 */

fun printPerson(person: Person?) {
    // let (일시적인 영역 생성) : scope function 의 한 종류
    // let : 확장함수, 람다를 받아 람다 결과를 반환한다.
    // --> block: (T) -> R (T를 파라미터로 하고 R을 반환하는 함수, 반환타입은 R)
    person?.let { // null이 아닐때 let 을 호출
        // 함수를 전달
        println(it.name) // it 으로 들어온 파라미터에 접근
        println(it.age)
    }

//    if (person != null) {
//        println(person.name)
//        println(person.age)
//    }
}