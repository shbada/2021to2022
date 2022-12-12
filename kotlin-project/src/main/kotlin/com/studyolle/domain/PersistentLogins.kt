package com.studyolle.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
class PersistentLogins (
    @Id
    @Column(length = 64)
    val series: String? = null,

    @Column(nullable = false, length = 64)
    val username: String? = null,

    @Column(nullable = false, length = 64)
    val token: String? = null,

    @Column(name = "last_used", nullable = false, length = 64)
    val lastUsed: LocalDateTime? = null,
) {

}