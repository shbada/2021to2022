package _15_코틀린에서_배열과_컬렉션을_다루는_방법

fun main() {
    val oldMap = mutableMapOf<Int, String>()
//    oldMap.put(1, "A")
    oldMap[1] = "MONDAY"
    oldMap[2] = "TUESDAY"

    // 중위 호출 (Pair 클래스를 만들어주고 전달)
    mapOf(1 to "MONDAY", 2 to "TUESDAY")

    for (key in oldMap.keys) {
        println(key)
        println(oldMap.get(key))
        println(oldMap[key])
    }

    for ((key, value) in oldMap.entries) {
        println(key)
        println(value)
    }
}