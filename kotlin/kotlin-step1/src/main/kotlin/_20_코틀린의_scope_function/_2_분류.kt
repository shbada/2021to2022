package _20_코틀린의_scope_function

import _19_코틀린의_이모저모.Person

/*
(scope function, 확장함수)
let
- 람다의 결과를 반환  (it 사용)
- 일반 함수를 받는다
run
- 람다의 결과를 반환  (this 사용)
- 확장함수를 받는다. (본인 자신을 this로 호출하고 생략할 수 있었다)
also
- 객체의 그 자체를 반환  (it 사용)
apply
- 객체의 그 자체를 반환  (this 사용)

> this : 생략이 가능한 대신, 다른 이름을 붙일 수 없다.
> it : 생략이 불가능한 대신, 다른 이름을 붙일 수 있다.

with (얘만 확장함수가 아니다)
- this를 사용해서 접근하고, this는 생략이 가능하다. with(파라미터, 람다)
 */
val person = Person("KIM", 100)

val value1 = person.let { // age
//    it.age // return
    // > it : 생략이 불가능한 대신, 다른 이름을 붙일 수 있다.
    p -> p.age
}

val value2 = person.run { // age
//    this.age
    // this : 생략이 가능한 대신, 다른 이름을 붙일 수 없다.
    age
}

val value3 = person.also { // person
    it.age
}

val value4 = person.apply { // person
    this.age
}

// with
//with(person) {
//    println(name)
//    println(this.age)
//}