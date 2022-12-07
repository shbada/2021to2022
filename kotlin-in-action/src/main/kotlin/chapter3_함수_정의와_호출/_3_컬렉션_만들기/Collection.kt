package chapter3_함수_정의와_호출._3_컬렉션_만들기

fun main() {
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three") // to : 일반함수

    // 객체가 어떤 클래스에 속하는지 추측하기
    println(set.javaClass) // Java의 getClass()
    println(list.javaClass)
    println(map.javaClass)
    /*
    코틀린은 자신만의 컬렉션 기능을 제공하지 않는다.
    자바 개발자가 기존 자바 컬렉션을 활용할 수 있다는 뜻이다.

    왜?
    표준 자바 컬렉션을 활용하면 자바 코드와 상호작용하기가 훨씬 더 쉽다.
    자바에서 코틀린 함수를 호출하거나 코틀린에서 자바 함수를 호출할때 자바와 코틀린 컬렉션을 서로 변환할 필요가 없다.
    코틀린 컬렉션은 자바 컬렉션과 똑같은 클래스고, 자바보다 더 많은 기능을 사용할 수 있다.
     */

    // 리스트의 마지막 원소를 가져올 수 있다.
    val strings = listOf("first", "Second", "fourteenth")
    println(strings.last())

    // 수로 이뤄진 컬렉션에서 최댓값을 찾을 수 있다.
    val numbers = setOf(1, 14, 8);
//    println(numbers.max())
}