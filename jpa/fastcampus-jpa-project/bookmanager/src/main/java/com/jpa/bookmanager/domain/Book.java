package com.jpa.bookmanager.domain;

import com.jpa.bookmanager.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
// @EntityListeners(value = MyEntityListener.class)
// @EntityListeners(value = AuditingEntityListener.class)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;

    // private Long publisherId;

    /* mappedBy 사용하여 아래는 주석처리 */
    //@OneToOne /* 1:1 연관관계 매핑 -> book 테이블에 book_review_info_id 컬럼이 생겼음 */
    @OneToOne(mappedBy = "book") /* 1:1 연관관계 매핑 -> mappedBy : book 에 FK를 두지않겠다 */
    @ToString.Exclude // ToString 순환참조 발생하므로 추가
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id") // @OneToMany 일때 중간테이블 생성하지 않도록 지정
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @ToString.Exclude
    private Publisher publisher;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }

//    @ManyToMany
//    @ToString.Exclude
//    private List<Author> authors = new ArrayList<>();

    // 등록 메소드 별도 생성
//    public void addAuthor(Author... author) {
//        Collections.addAll(this.authors, author);
//    }

    // user

    // User/Product (중간테이블 user_product 생성-> order)

    // Product

//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

    /** entity listener : @EntityListeners(value = MyEntityListener.class) */
//    @PrePersist
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
}
