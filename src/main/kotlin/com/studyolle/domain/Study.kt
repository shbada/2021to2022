package com.studyolle.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Study(
    @Id
    @GeneratedValue
    private val id: Long? = null,

    /* study_managers 테이블 생성 */
    @ManyToMany
    val managers: MutableSet<Account> = HashSet(),

    /* study_members 테이블 생성 */
    @ManyToMany
    val members: MutableSet<Account> = HashSet(),

    @Column(unique = true)
    val path: String? = null,
    val title: String? = null,
    val shortDescription: String? = null,

    @Lob
    @Basic(fetch = FetchType.EAGER)
    val fullDescription: String? = null,

    @Lob
    @Basic(fetch = FetchType.EAGER)
    val image: String? = null,

    /* study_tags 테이블 생성 */
    @ManyToMany
    val tags: Set<Tag> = HashSet(),

    /* study_zones 테이블 생성 */
    @ManyToMany
    val zones: Set<Zone> = HashSet(),

    var publishedDateTime: LocalDateTime? = null,
    var closedDateTime: LocalDateTime? = null,
    var recruitingUpdatedDateTime: LocalDateTime? = null,
    var recruiting : Boolean = false,
    var published : Boolean = false,
    var closed : Boolean = false,
    val useBanner : Boolean = false,
    var memberCount : Int = 0,
) {

}