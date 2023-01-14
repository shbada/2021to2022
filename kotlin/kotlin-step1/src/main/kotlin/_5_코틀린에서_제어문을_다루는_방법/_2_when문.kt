package _5_코틀린에서_제어문을_다루는_방법

fun getGradeWithSwitch(score: Int): String {
    return when (score / 10) {
        in 90..99 -> "A" // 어떠한 expression이라도 들어갈 수 있다.
        8 -> "B"
        7 -> "C"
        else -> "D"
    }
}

fun startsWithA(obj: Any): Boolean {
    return when (obj) {
        // String type이라면
        is String -> obj.startsWith("A")
        else -> false
    }
}

fun judgeNumber(number: Int) {
    when (number) {
        1, 0, -1 -> println("어디서 많이 본 숫자입니다")
        else -> println("1, 0, -1이 아닙니다")
    }
}

fun judgeNumber2(number: Int) {
    when {
        number == 0 -> println("주어진 숫잔는 0입니다.")
        number % 2 == 0 -> println("주어진 숫자는 짝수입니다.")
        else -> println("주어지는 숫자는 홀수입니다.")
    }
}