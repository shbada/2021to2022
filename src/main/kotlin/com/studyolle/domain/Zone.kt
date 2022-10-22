package com.studyolle.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Zone(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    val city: String? = null,

    @Column(nullable = false)
    val localNameOfCity: String? = null,

    @Column(nullable = true)
    val province: String? = null,
) {

}