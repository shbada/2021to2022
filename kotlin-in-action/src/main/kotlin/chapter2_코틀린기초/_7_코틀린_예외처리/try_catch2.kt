package chapter2_코틀린기초._7_코틀린_예외처리

import java.io.BufferedReader
import java.io.StringReader

fun main() {
    val reader = BufferedReader(StringReader("not a number"))
    readNumber2(reader)
    readNumber3(reader)
}

fun readNumber2(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        // NumberFormatException 가 발생했을 경우, 아무것도 출력되지 않는다.
        return
    }

    println(number)
}

fun readNumber3(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        // null 을 출력한다.
        null
    }

    println(number)
}