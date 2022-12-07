package _5_코틀린에서_제어문을_다루는_방법

import java.lang.IllegalArgumentException

fun validationSourceIsNotNegative(score: Int) { // void 명시 없다
    if (score < 0) {
        // new 사용 없다
        throw IllegalArgumentException("${score}는 0보다 작을 수 없다.")
    }

    // 0과 100 사이에 포함되지 않을 경우
    if (score !in 0..100) {
        throw IllegalArgumentException("score의 범위는 0부터 100입니다.")
    }

    if (score in 0..100) {

    }
}

/*
if~else
- Statement : 프로그램의 문장 (JAVA)
- Expression : 하나의 값으로 도출되는 문장 (Kotlin)
> JAVA 에서는 3항 연산자로 하나의 값으로 취급할수는 있다.
> Kotlin에는 3항연산자가 없다.
 */
fun getPassOrFail(score: Int): String {
    return if (score >= 50) {
        return "P"
    } else {
        return "F"
    }
}

fun getGrade(score: Int): String {
    return if (score >= 90) {
        return "A"
    } else if (score >= 80) {
        return "B"
    } else {
        return "D"
    }
}