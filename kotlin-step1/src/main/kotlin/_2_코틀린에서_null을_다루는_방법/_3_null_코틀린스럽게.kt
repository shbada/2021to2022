package _2_코틀린에서_null을_다루는_방법

import java.lang.IllegalArgumentException

fun main() {

}

fun startsWithA1Kt(str: String?) :Boolean {
    return str?.startsWith("a")
        ?: throw IllegalArgumentException("null이 들어왔습니다.")
}

fun startsWithA2Kt(str: String?): Boolean? {
    return str?.startsWith("A") // null 이면 null 을 그대로 반환
}

fun startsWithA3Kt(str: String?): Boolean {
    return str?.startsWith("A") ?: false
}