package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("JPA");
        book.setName("fast");
        book.setAuthorId(1L);
       // book.setPublisherId(1L);

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        Publisher getPublisher = publisherRepository.save(publisher);

        book.setPublisher(getPublisher);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    /**
     * TODO User join ??
     */
    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        User user = userRepository.findByEmail("test@naver.com");
        System.out.println(user);

        List<Review> reviewList = user.getReviews();

        System.out.println("Review : " + reviewList);
        System.out.println("Book : " + reviewList.get(0).getBook());
        System.out.println("Publisher : " + reviewList.get(0).getBook().getPublisher());
    }

    private void givenBookAndReview() {
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private User givenUser() {
        User user = new User();
        user.setName("kimseohae");
        user.setEmail("test@naver.com");
        user.setGender(Gender.FEMALE);

        return userRepository.save(user);
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("내 인생을 바꾼 책");
        review.setContent("너무너무 재미있고 즐거운 책이었어요.");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        return publisherRepository.save(publisher);
    }
}