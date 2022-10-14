package com.group.libraryapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LibraryAppApplication

// top line에 함수를 만드는 경우 static 함수처럼 간주된다.
fun main(args: Array<String>) {
    // 확장함수
    runApplication<LibraryAppApplication>(*args) // 가변인자 *args
}