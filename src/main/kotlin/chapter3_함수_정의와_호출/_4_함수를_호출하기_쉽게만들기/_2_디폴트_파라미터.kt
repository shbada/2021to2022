package chapter3_함수_정의와_호출._4_함수를_호출하기_쉽게만들기

/*
자바에서는 일부 클래스에서 오버로딩한 메서드가 너무 많아진다는 문제가 있다.
코틀린에서는 함수 선언 시에 파라미터의 디폴트 값을 지정할 수 있으므로 이런 오브로드 중 상당수를 피할 수 있다.
디폴트 값을 사용하여 joinToString 함수를 개선해보자.
대부분의 경우 아무 접두사나 접미사 없이 콤마로 원소를 구분한다.
그런 값을 디폴트로 지정하자.
 */
fun main() {
    val list = listOf(1, 2, 3)

    // 함수를 호출할때 모든 인자를 쓸 수도 있고, 일부를 생략할 수도 있다.
    println(defaultJoinToString(list, ", ", "", "")) // 1, 2, 3
    println(defaultJoinToString(list)) // 1, 2, 3
    println(defaultJoinToString(list, "; ")) // 1; 2; 3
}

fun <T> defaultJoinToString( // default 값이 지정된 파라미터들
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)  // 첫 원소 앞에는 구분자를 붙이면 안된다.
        result.append(element)
    }

    result.append(postfix)

    return result.toString()
}