package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long>, UserRepositoryCustom {
//    fun findByName(name: String) : Optional<User>
    fun findByName(name: String) : User?

    // JPA에서 만든 JPQL이 여기 들어가야 한다.
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userLoanHistoryList")
    fun findAllWithHistoryList(): List<User>
}