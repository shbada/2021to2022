package _3_코틀린에서_Type을_다루는_방법

import _2_코틀린에서_null을_다루는_방법.Person

fun main() {
    /*
    Spring interpolation : ${변수}
    String indexing
     */
    val person = Person("KIM")
    val age = 22

    println("이름 : ${person.name}")
    println("나이 : $age")

    /*
    """ (JAVA에서는 append)
     */
    val str = """
        ABCDE
        EFG
        AJWJKQLWK
    """.trimIndent()

    print(str)

    /*
    특정 문자 가져오기
     */
    val str2 = "ABC"
    print(str2[0])
    print(str2[1])
}