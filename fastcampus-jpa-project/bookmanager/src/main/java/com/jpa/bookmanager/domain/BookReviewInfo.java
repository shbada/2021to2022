package com.jpa.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 제거 후 Book 을 직접 참조
    // private Long bookId;
    // book_review_info 테이블에 book_id 값이 생겼다
    /** optional = false : book 은 반드시 존재하는 값으로 null 허용을 하지 않는다. -> inner join 으로 변경 */
    // mappedBy = "bookReviewInfo" : 연관키를 해당 테이블에 더이상 가지지 않게된다.
    // @OneToOne(optional = false, mappedBy = "bookReviewInfo") /* 1:1 연관관계 매핑 */
    @OneToOne(optional = false) /* 1:1 연관관계 매핑 */
    private Book book;

    // float, int : primitive type (small float, small integer 을 사용한 이유는 null 허용 여부의 선택이다) : null 허용X
    // JPA 에서 not null 로 들어가게된다.
    private float averageReviewScore;

    private int reviewCount;
}
