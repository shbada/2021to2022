package _2_코틀린에서_null을_다루는_방법

fun main() {
    var str: String? = "ABC"
//    str.length  // 불가능
    str?.length // str이 null이 아닐 경우에 함수를 호출하겠다. null 이라면 결과가 null이다.

    // Safe Call
    str = null
    print(str?.length)

    // Elvis
    print(str?.length ?: 0) // 앞의 결과가 null 이면 전체 식의 결과가 0이다.
}

fun calculate(number: Long?): Long {
    // early return
    number ?: return 0

    // 다음 로직 사용 가능

    return 1
}