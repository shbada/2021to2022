package com.example.jpa.practice;

import com.example.jpa.enums.EventType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Builder 를 쓰면 Set 과 같은 필드가 null 로 들어간다.
 */
@NamedEntityGraph(
        name = "Event.withEnrollments",
        attributeNodes = @NamedAttributeNode("enrollments")
)
@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
public class Event {

    @Id @GeneratedValue
    private Long id;

    /**
     * study_id bigint, (FK)
     */
    @ManyToOne // Many 의 테이블에 FK 생성
    private Study study;

    /**
     * 모임 생성한 회원 정보
     */
    @ManyToOne
    private Account createdBy; // timestamp

    @Column(nullable = false)
    private String title;

    @Lob
    private String description; // clob

    @Column(nullable = false)
    private LocalDateTime createdDateTime; // timestamp

    @Column
    private Integer limitOfEnrollments; // integer

    /**
     * event_id (FK)
     */
    @OneToMany(mappedBy = "event")
    @OrderBy("enrolledAt")
    private List<Enrollment> enrollments = new ArrayList<>();

    /**
     * varchar(255) 이지만 아래 enum 의 값만 들어가겠다 STRING 자체로
     */
    @Enumerated(EnumType.STRING)
    private EventType eventType;
}
