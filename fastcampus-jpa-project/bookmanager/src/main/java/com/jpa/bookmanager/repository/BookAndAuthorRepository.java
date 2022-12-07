package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Author;
import com.jpa.bookmanager.domain.BookAndAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAndAuthorRepository extends JpaRepository<BookAndAuthor, Long> {
}
