package _8_코틀린에서_함수를_다루는_방법

fun main() {
    // 매개변수 이름을 명시해줘서, 지정되지 않은 매개변수는 기본값을 사용 가능하다.
    // num 이 두번재 인자였는데 이렇게 함으로써 default value를 사용한다.
    repeat2("Hello World", useNewLine = true)

    printNameAndGender("", "")
}

// 주어진 문자열을 N번 출력하는 예제
fun repeat2(
    str: String,
    num: Int = 3, // default 3
    useNewLine: Boolean = true // default true
) {
    for (i in 1..num) {
        if (useNewLine) {
            println(str)
        } else {
            print(str)
        }
    }
}

fun printNameAndGender(name: String, gender: String) {
    println(name)
    println(gender)
}