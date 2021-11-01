package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Book;
import com.jpa.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewInfoRepositoryTest {
    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    /**
     * Book, BookReviewInfo
     */
    @Test
    void crud_rdb() {
        /* RDB 방식 */
        givenBookReviewInfo();

        // 연관관계
        // id=2, bookId=1 : Id 가 AUTO INCREMENT 이다.
//        Book result = bookRepository.findById(
//                bookReviewInfoRepository.findById(1L).orElseThrow(RuntimeException::new).getBookId()
//        ).orElseThrow(RuntimeException::new);

        Book result = bookReviewInfoRepository.findById(1L).orElseThrow(RuntimeException::new).getBook();

        System.out.println(result);

        BookReviewInfo result2 = bookRepository.findById(1L).orElseThrow(RuntimeException::new).getBookReviewInfo();
        System.out.println(result2);
    }

    /**
     * BookReviewInfo test
     */
    @Test
    void crud() {
        givenBookReviewInfo();
    }

    private Book givenBook() {
        Book book = new Book();
        book.setName("Jpa");
        book.setAuthorId(1L);
        // book.setPublisherId(1L);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook()); // left outer join -> inner join (entity 파일에서 optional=false 지정)
        // bookReviewInfo.setBook(1L);
        bookReviewInfo.setReviewCount(1);
        bookReviewInfo.setAverageReviewScore(4.5f);
        // createdDate, updatedDate 는 Auditable 에서 선언해줬으므로 생략

        bookReviewInfoRepository.save(bookReviewInfo);

        // book_review_info bookreview0_ left outer join book book1_  : 조인 발생 
        System.out.println(bookReviewInfoRepository.findAll());
    }
}