package chapter3_함수_정의와_호출._5_확장함수_확장프로퍼티

/*
Collection<T> 에 대한 확장 함수를 선언한다.
 */
fun <T> Collection<T>.joinToString(
    // default 값 지정한다.
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    // 수신 객체 : this
    // T 타입의 원소로 이루어진 컬렉션이다.
    for ((index, element) in this.withIndex()) { // this는 수신 객체(= T타입 컬렉션)
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3)
    println(list.joinToString(separator = "; ", prefix = "(", postfix = ")"))

    val arrayList = arrayListOf(1, 2, 3)
    println(arrayList.joinToString(" "))

    println(listOf("one", "two").join(" "))
    // 오류 발생
//    println(listOf(1, 2, 3).join(" "))
}

// 확장 함수는 단지 정적 메서드 호출에 대한 문법적인 편의다.
// 그래서 클래스가 아닌 더 구체적인 타입을 수신 객체 타입으로 지정할 수도 있다.
// 문자열의 컬렉션에 대해서만 호출할 수 있는 Join 함수를 정의해보자.
fun Collection<String>.join(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = joinToString(separator, prefix, postfix)