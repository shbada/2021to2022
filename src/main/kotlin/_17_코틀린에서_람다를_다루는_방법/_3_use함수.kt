package _17_코틀린에서_람다를_다루는_방법

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/*
use
Closeable 구현체에 대한 확장 함수다.
fun <T : Closable?, R> T.use(block: (T) -> R): R { ... }

- 인라인 함수
- 람다를 받게 만들어진 함수
 */
class FilePrinter2 {
    fun readFile(path: String) {
        // try~catch~resource 구문 자체는 사라지고 use를 사용한다.
        BufferedReader(FileReader(path)).use { // lambda
                reader -> println(reader.readLine())
        }
    }
}