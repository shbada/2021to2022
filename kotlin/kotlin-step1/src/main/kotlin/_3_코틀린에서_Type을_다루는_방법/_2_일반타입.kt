package _3_코틀린에서_Type을_다루는_방법

import _2_코틀린에서_null을_다루는_방법.Person

fun main() {
    printAgeOfPerson2(null) // null 을 보내보자.
    printAgeOfPerson2(Person("seohae")) // null 을 보내보자.
}

fun printAgeIfPerson(obj: Any) {
    if (obj is Person) { // instanceof -> is
        val person = obj as Person // (Person) obj (생략도 가능)
        println(person.name)
        println(obj.name) // smart cast
    }

    if (!(obj is Person)) { // instanceof not -> is
        println(obj)
    }

    if (obj !is Person) { // instanceof not -> is
        println(obj)
    }
}

fun printAgeOfPerson2(obj: Any?) {
    val person = obj as? Person // as? : obj가 null 이 아니라면 Person, 아니라면 전체 결과값이 null
    val person2: Person? = obj as? Person // as? : obj가 null 이 아니라면 Person, 아니라면 전체 결과값이 null

    println(person?.name) // null 이들어가면 null
}