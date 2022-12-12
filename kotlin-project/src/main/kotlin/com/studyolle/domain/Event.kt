package com.studyolle.domain

import com.studyolle.domain.enums.EventType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Event(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @ManyToOne
    val study: Study? = null,

    /**
     * 모임 생성한 회원 정보
     */
    /* event 테이블의 컬럼 : created_by_id */
    @ManyToOne
    val createdBy: Account? = null,

    @Column(nullable = false)
    val title: String? = null,

    @Lob
    val description: String? = null,

    @Column(nullable = false)
    val createdDateTime: LocalDateTime? = null,

    @Column(nullable = false)
    val endEnrollmentDateTime: LocalDateTime? = null,

    @Column(nullable = false)
    val startDateTime: LocalDateTime? = null,

    @Column(nullable = false)
    val endDateTime: LocalDateTime? = null,

    @Column
    val limitOfEnrollments: Int? = null,

    /**
     * 참여정보
     */
    @OneToMany(mappedBy = "event")
    @OrderBy("enrolledAt")
    val enrollments: MutableList<Enrollment> = ArrayList(),

    /**
     * EvenType
     * FCFS 선착순
     * CONFIRMATIVE 관리자 확인 필요
     */
    @Enumerated(EnumType.STRING)
    val eventType: EventType? = null,
) {
}