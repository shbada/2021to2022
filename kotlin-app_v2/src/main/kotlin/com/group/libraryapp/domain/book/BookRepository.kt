package com.group.libraryapp.domain.book

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.dto.book.response.BookStatResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookRepository : JpaRepository<Book, Long> {
    // kotlin 에서 ?를 사용해서 Book? 가 null이 가능해져서 Optional 을 제외할 수는 있다.
    // Service와의 호환성을 위해 Optional 을 그대로 써주자.
//    fun findByName(bookName: String) : Optional<Book>
    fun findByName(bookName: String) : Book?

    @Query(
        "SELECT NEW com.group.libraryapp.dto.book.response.BookStatResponse(b.type, COUNT(b.id)) FROM Book b GROUP BY b.type"
    )
    fun getStats(): List<BookStatResponse>
}