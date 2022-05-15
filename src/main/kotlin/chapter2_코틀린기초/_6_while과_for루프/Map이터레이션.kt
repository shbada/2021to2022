package chapter2_코틀린기초._6_while과_for루프

import java.util.*

fun main() {
    // key 에 대한 정렬을 위해 treeMap 사용
    val binaryReps = TreeMap<Char, String>()

    for(c in 'A'..'F') { // A 부터 F까지
        val binary = Integer.toBinaryString(c.toInt()) // 아스키코드 -> 2진표현으로 바꾼다.
        binaryReps[c] = binary // key : c, value : 2진표현
//        binaryReps.put(c, binary) // 위 코드와 동일
    }

    // Map에 대해 이터레이션한다.
    // Map의 Key와 value를 두 변수에 각각 대입한다.
    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }

    val list = arrayListOf("10", "11", "1001")

    // 인덱스와 함께 컬렉션을 이터레이션한다.
    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }
}