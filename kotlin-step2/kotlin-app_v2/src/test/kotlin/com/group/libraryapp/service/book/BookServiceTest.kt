package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.book.BookType
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanHistory.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
    private val bookRepository: BookRepository, // 마지막 줄에 , 써도 된다.
) {
    @AfterEach
    fun clean() {
        userRepository.deleteAll()
        bookRepository.deleteAll() // 자식 데이터까지 제거 (UserLoanHistoryRepository delete 까지 해준다)
    }

    @Test
    fun saveBook() {
        // given
        val request = BookRequest("A", BookType.COMPUTER)

        // when
        bookService.saveBook(request)

        // then
        val books = bookRepository.findAll()
        assertThat(books).hasSize(1)
        assertThat(books[0].name).isEqualTo("A")
        assertThat(books[0].type).isEqualTo(BookType.COMPUTER)
    }

    @Test
    @DisplayName("책 대출이 정상적으로 작동한다.")
    fun loanBook() {
        // given
        /** Book 에 필드를 추가해도 테스트 코드에는 영향을 주고싶지 않다. - fixture 메서드 생성 (companion object) */
        bookRepository.save(Book.fixture("A"))
        val savedUser = userRepository.save(User("KIM", null))
        val request = BookLoanRequest("KIM", "A")

        // when
        bookService.loanBook(request)

        // then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].bookName).isEqualTo("A")
        assertThat(results[0].user.id).isEqualTo(savedUser.id)
        assertThat(results[0].status).isEqualTo(UserLoanStatus.LOANED)
    }

    @Test
    @DisplayName("책이 대출되어있다면, 신규 대출이 실패한다.")
    fun loadBookFailTest() {
        // given
        bookRepository.save(Book.fixture("A"))
        val savedUser = userRepository.save(User("KIM", null))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(savedUser, "A"))
        val request = BookLoanRequest("KIM", "A")

        // when & then
        val message = assertThrows<IllegalArgumentException> {
            bookService.loanBook(request)
        }.message
        assertThat(message).isEqualTo("이미 대출되어있는 책입니다")

    }

    @Test
    @DisplayName("책 반납이 정상 동작한다")
    fun returnBook() {
        // given
//        bookRepository.save(Book("A"))
        val savedUser = userRepository.save(User("KIM", null))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(savedUser, "A"))
        val request = BookReturnRequest("KIM", "A")


        // when
        bookService.returnBook(request)

        // then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].status).isEqualTo(UserLoanStatus.RETURNED) // 반납 완료 (true)
    }

    @Test
    @DisplayName("책 대여 권수를 정상 확인한다")
    fun countLoanedBookTest() {
        // given
        val savedUser = userRepository.save(User("최태현", null))
        userLoanHistoryRepository.saveAll(
            listOf(
                UserLoanHistory.fixture(savedUser, "A"),
                UserLoanHistory.fixture(savedUser, "B", UserLoanStatus.RETURNED),
                UserLoanHistory.fixture(savedUser, "C", UserLoanStatus.RETURNED),
            )
        )

        // when
        val result = bookService.countLoanedBook()

        // then
        assertThat(result).isEqualTo(1)
    }

    @Test
    @DisplayName("분야별 책 권수를 정상 확인한다")
    fun getBookStatisticsTest() {
        // given
        bookRepository.saveAll(listOf(
            Book.fixture("A", BookType.COMPUTER),
            Book.fixture("B", BookType.COMPUTER),
            Book.fixture("C", BookType.SCIENCE),
        ))

        // when
        val result = bookService.getBookStatistics()

        // then
        assertThat(result).hasSize(2)
        assertCount(result, BookType.COMPUTER, 2L)
        assertCount(result, BookType.SCIENCE, 1L)
    }

    private fun assertCount(result: List<BookStatResponse>, type: BookType, expectedCount: Long) {
        assertThat(result.first { it.type == type }.count).isEqualTo(expectedCount)
    }
}