package com.jpa.bookmanager.service;

import com.jpa.bookmanager.domain.Author;
import com.jpa.bookmanager.domain.Book;
import com.jpa.bookmanager.repository.AuthorRepository;
import com.jpa.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void transactionTest() {
        // 테스트 코드에서 try~catch 는 쓰면 안된다(안티패턴).
        // 연습을 위해 사용
        try {
            // bookService.putBookAndAuthor();
            bookService.put();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("authors : " + authorRepository.findAll());
    }

    @Test
    void isolationTest() {
        Book book = new Book();
        book.setName("1번 책");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println(">>> " + bookRepository.findAll());
    }
}