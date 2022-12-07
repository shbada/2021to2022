package _7_코틀린에서_예외를_다루는_방법

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/**
 * Kotlin 에서는 CatchException, UncheckedExcpetion 을 구분하지 않는다.
 * 모두 UncheckedException 이다.
 * 따라서 에러를 던지지 않아도 된다.
 */
class FilePrinter {
    fun readFile() {
        val currentFile = File(".")
        val file = File(currentFile.absolutePath + "/a.txt")
        val reader = BufferedReader(FileReader(file))
        println(reader.readLine())
        reader.close()
    }
}