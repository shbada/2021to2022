package chapter2_코틀린기초._6_while과_for루프

fun main() {
    println(isLetter('q'))
    println(isNotDigit('x'))
    println(recognize('8'))

    // "Java" <= "Kotlin" && "Kotlin" <= "Scala"
    println("Kotlin" in "Java".."Scala")
}

// 글자인지 체크
//  c in 'a'..'z' -> 'a' <= c && c <= 'z'
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
// 숫자가 아닌지 체크
fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!" // c 값이 0부터 9 사이에 있는지 검사한다.
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know..."
}



