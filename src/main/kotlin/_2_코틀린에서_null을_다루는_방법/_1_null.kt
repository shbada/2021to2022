package _2_코틀린에서_null을_다루는_방법

import java.lang.IllegalArgumentException

fun main() {

}

fun startsWithA1(str: String?) :Boolean {
    if (str == null) {
        throw IllegalArgumentException("null이 들어왔습니다.")
    }

    return str.startsWith("A")
}

fun startsWithA2(str: String?): Boolean? {
    if (str == null) {
        return null
    }

    return str.startsWith("A")
}

fun startsWithA3(str: String?): Boolean {
//    String? 일때 null이 가능하므로 바로 함수 호출이 불가능하다.
//    return str.startsWith("A")

    if (str == null) {
        return false
    }

    // 초록색 표시 : 위에서 null 체크가 되면 이 str은 이 시점에 null이 아니라는 표시다.
    return str.startsWith("A")
}