package _7_코틀린에서_예외를_다루는_방법

fun main() {

}

fun parseIntOrThrow(str: String): Int {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("주어진 ${str}는 숫자가 아닙니다.")
    }
}

// return
fun parseIntOrThrowV2(str2: String): Int? {
    return try {
        return str2.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}