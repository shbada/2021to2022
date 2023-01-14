package _12_코틀린에서_object키워드를_다루는_방법

// 싱글톤 : 단 하나의 인스턴스만 갖음 (object 키워드)

fun main() {
    // 유일한 객체이므로 바로 접근 가능하다.
    Singleton.a
    Test.a
}

object Singleton {
    var a: Int = 0
}

object Test {
    var a: Int = 0
}