package chapter3_함수_정의와_호출._4_함수를_호출하기_쉽게만들기

fun main() {
    val list = listOf(1, 2, 3)
    println(list) // [1, 2, 3]

    // (1; 2; 3) 처럼 원소 사이를 세미콜론(;)으로 구분하고 괄호로 리스트를 둘러싸고싶다면?
    // 코틀렌이는 이런 요구 사항을 처리할 수 있는 함수가 표준 라이브러리에 이미 들어있다.

    // 코틀린이 지원하는 기능을 사용하지 않고 직접 구현해보자.

    // joinToString() : 컬렉션의 원소를 StringBuilder의 뒤에 덧붙인다.
    // 이때 원소 사이에 구분자(separator)를 추가하고,
    // StringBuilder의 맨 앞과 맨 뒤에는 접두사(prefix), 접미사(postfix)를 추가한다.
    println(joinToString(list, "; ", "(", ")")) // (1; 2; 3)
    /*
    위 4개의 인자를 보자.
    함수의 시그니처를 바로 파악하기 어려우며, 함수 호출 코드 자체가 모호하다.
    이런 문제는 특히 불리언 플래그(flag) 값을 전달해야 하는 경우 흔히 발생한다.
    이를 해결하기 위해 일부 자바 코딩 스타일에서는 불리언 대신 enum 타입을 사용하라고 권장한다.
     */
    // 코틀린에서는 함수에 전달하는 인자 중 일부의 이름을 명시할 수 있다.
    // 호출시 인자 중 어느 하나라도 이름을 명시하고 나면 혼동을 막기위해 그 뒤에 오는 모든 인자는 이름을 꼭 명시해야한다.
    println(joinToString(collection = list, separator = "; ", prefix = "(", postfix = ")"))
}

/*
이 함수는 어떤 타입의 값을 원소로 하는 컬렉션이든 처리할 수 있다.
 */
fun <T> joinToString(
    collection: Collection<T>,
    separator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)  // 첫 원소 앞에는 구분자를 붙이면 안된다.
        result.append(element)
    }

    result.append(postfix)

    return result.toString()
}
