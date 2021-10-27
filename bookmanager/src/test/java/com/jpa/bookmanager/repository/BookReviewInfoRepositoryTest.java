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
        Book book = new Book();
        book.setName("Jpa");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        bookRepository.save(book);

        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " + bookReviewInfoRepository.findAll());

        // 연관관계
        // id=2, bookId=1 : Id 가 AUTO INCREMENT 이다.
        Book result = bookRepository.findById(
                bookReviewInfoRepository.findById(1L).orElseThrow(RuntimeException::new).getBookId()
        ).orElseThrow(RuntimeException::new);

        System.out.println(result);
    }

    /**
     * BookReviewInfo test
     */
    @Test
    void crud() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setReviewCount(1);
        bookReviewInfo.setAverageReviewScore(4.5f);
        // createdDate, updatedDate 는 Auditable 에서 선언해줬으므로 생략

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(bookReviewInfoRepository.findAll());
    }
}