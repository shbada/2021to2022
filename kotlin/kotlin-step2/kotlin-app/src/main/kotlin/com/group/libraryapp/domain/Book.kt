package com.group.libraryapp.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Book( // 기본 생성자 필요 (build.gradle 에 'org.jetbrains.kotlin.plugin.jpa' 추가로 해결)
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // default null
) {
    // 초기화 블록
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어있을 수 없습니다")
        }
    }
}