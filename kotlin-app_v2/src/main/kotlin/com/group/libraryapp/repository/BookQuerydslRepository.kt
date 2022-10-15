package com.group.libraryapp.repository

import com.group.libraryapp.domain.book.QBook.book
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

/**
 * 장점 : 클래스만 바로 만들면 되어 간결하다는 점
 * 단점 : 서비스단에서 필요에 따라 두 Repository를 모두 사용해주어야 한다는 점
 */
@Component // JPAQueryFactory 를 주입 받을 수 있도록 @Component 어노테이션 선언
class BookQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {
    fun getStats(): List<BookStatResponse> {
        return queryFactory
            .select(
                Projections.constructor( // 주어진 DTO의 생성자를 호출
                    // 'select book.type, count(book.id) from book '와 동일
                    BookStatResponse::class.java,
                    book.type,
                    book.id.count(),
                )
            )
            .from(book)
            .groupBy(book.type) // 'group by type'
        .fetch()
    }
}