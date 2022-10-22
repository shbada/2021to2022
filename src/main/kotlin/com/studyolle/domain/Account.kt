package com.studyolle.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Account (
    @Id
    @GeneratedValue
    val id: Long? = null,

    /* 중복 불가능 */
    @Column(unique = true)
    val email: String? = null,

    @Column(unique = true)
    val nickname: String? = null,
    val password: String? = null,

    /* 이메일 검증 */
    var emailVerified : Boolean = false,
    var emailCheckToken: String? = null,
    var emailCheckTokenGeneratedAt: LocalDateTime? = null,
    var joinedAt : LocalDateTime? = null,
    val bio : String? = null, // 자기소개
    val url: String? = null,
    val occupation : String? = null,
    val location : String? = null,

    /* CLOB */
    @Lob
    @Basic(fetch = FetchType.EAGER)
    /* 즉시로딩 */
    val profileImage : String? = null,

    val studyCreatedByEmail : Boolean = false, // 스터디 생성 여부를 이메일로 받을것인가?
    val studyCreatedByWeb : Boolean = false, // 스터디 생성 여부를 웹으로 받을것인가?
    val studyEnrollmentResultByEmail : Boolean = false, // 스터디 모임 가입신청 결과를 이메일로 받을것인가?
    val studyEnrollmentResultByWeb : Boolean = false, // 스터디 모임 가입신청 결과를 웹으로 받을것인가?
    val studyUpdatedByEmail : Boolean = false, // 스터디 변경 정보를 이메일로 받을것인가?
    val studyUpdatedByWeb : Boolean = false, // 스터디 변경 정보를 웹으로 받을것인가?

    /* account_tags 테이블 생성 */
    @ManyToMany
    val tags: Set<Tag> = HashSet(),

    /* account_zones 테이블 생성 */
    @ManyToMany
    val zones: Set<Zone> = HashSet(),
) {
}
