package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanHistory.UserLoanStatus
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.util.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
) {
    @Transactional
    fun saveBook(request: BookRequest) {
        val book = Book(request.name, request.type)
        bookRepository.save(book)
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
//        val book = bookRepository.findByName(request.bookName).orElseThrow(::IllegalArgumentException)
//        val book = bookRepository.findByName(request.bookName) ?: throw IllegalArgumentException()
        val book = bookRepository.findByName(request.bookName) ?: fail()

        if (userLoanHistoryRepository.findByBookNameAndStatus(request.bookName, UserLoanStatus.LOANED) != null) {
            throw IllegalArgumentException("이미 대출되어있는 책입니다")
        }

        val user = userRepository.findByName(request.userName) ?: fail()
        user.loadBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        val user = userRepository.findByName(request.userName) ?: fail()
        user.returnBook(request.bookName)
    }

    @Transactional(readOnly = true)
    fun countLoanedBook(): Int {
//        return userLoanHistoryRepository.findAllByStatus(UserLoanStatus.LOANED).size
        return userLoanHistoryRepository.countByStatus(UserLoanStatus.LOANED).toInt()
    }

    @Transactional(readOnly = true)
    fun getBookStatistics(): List<BookStatResponse> {
//        return bookRepository.findAll() // List<Book>
//            .groupBy { book -> book.type } // Map<BookType, List<Book>>
//            .map { (type, books) -> BookStatResponse(type, books.size) }
        return bookRepository.getStats()
    }

//    @Transactional(readOnly = true)
//    fun getBookStatistics_deprecated(): List<BookStatResponse> {
//        // response List
//        val results = mutableListOf<BookStatResponse>() // 가변 리스트
//
//        // select book List
//        val books = bookRepository.findAll()
//
//        for (book in books) {
//            // ?.plusOne() : 앞에 연산이 null이면 plusOne() 수행
//            // ?.plusOne().?: : 존재하지 않은 경우에는 results.add 수행
//            results.firstOrNull { dto -> dto.type == book.type }?.plusOne()
//                ?: results.add(BookStatResponse(book.type, 1))
//
//        // 1) 책 구분별로 만들어놨던 응답 dto에서 방금 가져온 책의 타입과 같은 타입의 책이 존재한다면 3), 아니면 2)
////            val dto = results.firstOrNull { dto -> dto.type == book.type }
////
////            if (dto == null) { // 2) 최초 등록
////                results.add(BookStatResponse(book.type, 1))
////            } else { // 3) + 1
////                dto.plusOne()
////            }
//        }
//
//        return results
//    }
}