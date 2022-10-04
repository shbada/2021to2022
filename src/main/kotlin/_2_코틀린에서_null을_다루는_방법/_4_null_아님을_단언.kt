package _2_코틀린에서_null을_다루는_방법

fun main() {
    println(startsWith("AAA"))

    // null 아님을 명시했지만, null이 들어간다면?
    // -> 컴파일 에러 발생
    println(startsWith(null))
}

// 절대 null이 아니라는 명시
fun startsWith(str: String?): Boolean {
    return str!!.startsWith("A")
}
