package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Book;
import com.jpa.bookmanager.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewInfoRepository extends JpaRepository<BookReviewInfo, Long> {
}
