package com.jpa.bookmanager.service;

import com.jpa.bookmanager.domain.Author;
import com.jpa.bookmanager.domain.Book;
import com.jpa.bookmanager.repository.AuthorRepository;
import com.jpa.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    /**
     * @Transactional 추가 후
     * 1) Book insert
     * 2) Author insert
     * (디버깅 모드로 확인)
     * 여기서 DB에서 select 하면 아직 데이터가 insert 되지 않았다.
     * 그리고, 모든 실행 후 다시 select 하면 insert 되었다.
     *
     * 해당 쿼리들은 하나의 트랜잭션으로 묶여서 수행된다.
     * 그래서, 모든 쿼리가 성공적으로 실행된 시점에 반영을 위한 commit 이 실행된다.
     * 각각의 save 가 실행됬을때 아직 저장하지 않고 대기하고 있다가 트랜잭션이 완료되는 시점에 커밋되어 반영된다.
     *
     * @Transactional 은 메서드의 시작이 트랜잭션의 시작이되고, 메서드의 종료가 트랜잭션의 종료(커밋)이다.
     *
     */
    @Transactional
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

        // 예외 발생시, DB commit 이 발생하지 않는다.
        // throw new RuntimeException("예외가 발생하여 커밋이 발생하지 않는다.");
    }
}
