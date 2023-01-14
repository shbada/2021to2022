package _19_코틀린의_이모저모

const val number = 20

fun getNumberOrNull(): Int? {
    return if (number <= 0) {
        null
    } else {
        number
    }
}

// 주어진 조건을 만족하면 그 값이, 그렇지 않으면 null
fun getNumberOrNull2(): Int? {
    return number.takeIf { it > 0 }
}

// 주어진 조건을 만족하지 않으면 그 값이, 그렇지 않으면 null이 반환된다.
fun getNumberOrNull3(): Int? {
    return number.takeUnless { it <= 0 }
}