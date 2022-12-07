package com.studyolle.domain

import com.studyolle.domain.enums.NotificationType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Notification(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val title: String? = null,
    val link: String? = null,
    val message: String? = null,
    val checked : Boolean = false,

    @ManyToOne
    val account: Account? = null,

    val createdDateTime: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    val notificationType: NotificationType? = null,
) {
}