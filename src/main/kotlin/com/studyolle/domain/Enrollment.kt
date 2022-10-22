package com.studyolle.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Enrollment(
    @Id
    @GeneratedValue
    val id: Long? = null,

    /* 테이블 enrollment : 컬럼 event_id */
    @ManyToOne
    val event: Event? = null,

    /* 테이블 enrollment : 컬럼 account_id */
    @ManyToOne
    val account: Account? = null,

    val enrolledAt: LocalDateTime? = null,
    val accepted : Boolean = false,
    val attended : Boolean = false,
) {
}