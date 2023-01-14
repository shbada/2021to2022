package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Book;
import com.jpa.bookmanager.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
