package _15_코틀린에서_배열과_컬렉션을_다루는_방법

// 배열은 잘 쓰이지 않는다.
fun main() {
    val array = arrayOf(100, 200) // 배열 생성

    // index
    for (i in array.indices) {
        println("${i} ${array[i]}")
    }

    // index, value
    for ((idx, value) in array.withIndex()) {
        println("$idx $value")
    }

    array.plus(300)  // 배열에 새로운 element 추가
}