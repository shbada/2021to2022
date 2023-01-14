package _7_코틀린에서_예외를_다루는_방법

import java.io.BufferedReader
import java.io.FileReader

class FilePrinter2 {
    fun readFile(path: String) {
        // try~catch~resource 구문 자체는 사라지고 use를 사용한다.
        BufferedReader(FileReader(path)).use {
            reader -> println(reader.readLine())
        }
    }
}