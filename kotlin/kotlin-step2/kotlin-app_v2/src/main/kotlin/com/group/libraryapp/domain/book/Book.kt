package com.group.libraryapp.domain.book

import javax.persistence.*

@Entity
class Book( // 기본 생성자 필요 (build.gradle 에 'org.jetbrains.kotlin.plugin.jpa' 추가로 해결)
    val name: String,

    @Enumerated(EnumType.STRING) // 숫자(0, 1..) -> 문자열로 DB에 등록
    val type: BookType,

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

    // 가장 아래에 생성
    /** Object Mother : 테스트에 이용할 객체를 만드는 함수 - 이렇게 생겨난 테스트용 객체를 Test Fixture 라고 한다. */
    companion object {
        fun fixture(
            name: String = "책 이름",
            type: BookType = BookType.COMPUTER,
            id: Long? = null
        ): Book {
            return Book(
                name = name,
                type = type,
                id = id,
            )
        }
    }
}