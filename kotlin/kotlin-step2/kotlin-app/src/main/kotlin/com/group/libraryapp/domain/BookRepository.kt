package com.group.libraryapp.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BookRepository : JpaRepository<Book, Long> {
    // kotlin 에서 ?를 사용해서 Book? 가 null이 가능해져서 Optional 을 제외할 수는 있다.
    // Service와의 호환성을 위해 Optional 을 그대로 써주자.
//    fun findByName(bookName: String) : Optional<Book>
    fun findByName(bookName: String) : Book?
}