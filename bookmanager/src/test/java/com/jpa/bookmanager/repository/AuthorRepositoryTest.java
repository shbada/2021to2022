package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Author;
import com.jpa.bookmanager.domain.Book;
import com.jpa.bookmanager.domain.Publisher;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    void manyToManyTest() {
        Book book1 = givenBook("책1");
        Book book2 = givenBook("책2");
        Book book3 = givenBook("개발책1");
        Book book4 = givenBook("개발책2");

        Author author1 = givenAuthor("작가1");
        Author author2 = givenAuthor("작가2");

        book1.addAuthor(author1);
        book2.addAuthor(author2);
        book3.addAuthor(author1, author2);
        book4.addAuthor(author1, author2);

//        book1.setAuthors(Lists.newArrayList(author1));
//        book2.setAuthors(Lists.newArrayList(author2));
//        book3.setAuthors(Lists.newArrayList(author1, author2)); // 공동저자
//        book4.setAuthors(Lists.newArrayList(author1, author2)); // 공동저자

//        author1.setBooks(Lists.newArrayList(book1, book3, book4));
//        author2.setBooks(Lists.newArrayList(book2, book3, book4));

        author1.addBook(book1, book3, book4);
        author2.addBook(book2, book3, book4);

        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
        authorRepository.saveAll(Lists.newArrayList(author1, author2));

        System.out.println("author : " + bookRepository.findAll().get(2).getAuthors());
        System.out.println("books : " + authorRepository.findAll().get(0).getBooks());
    }

    private Book givenBook(String name) {
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }

    private Author givenAuthor(String name) {
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }
}