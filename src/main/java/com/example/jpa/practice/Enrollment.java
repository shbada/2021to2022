package com.example.jpa.practice;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
public class Enrollment {

    @Id @GeneratedValue
    private Long id; // bigint, PK

    /**
     * Event.java 에서 @OneToMany(mappedBy = "event") 로 되어있기 때문 // @OneToMany(mappedBy = "event")
     * event_id (FK)
     */
    @ManyToOne
    private Event event;

    /**
     * account_id (FK)
     */
    @ManyToOne // Many 의 테이블에 FK 생성
    private Account account;

    private LocalDateTime enrolledAt;

    private boolean accepted; // boolean

    private boolean attended; // boolean

}
