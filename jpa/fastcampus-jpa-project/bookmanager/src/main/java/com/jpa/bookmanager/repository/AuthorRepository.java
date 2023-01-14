package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Author;
import com.jpa.bookmanager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
