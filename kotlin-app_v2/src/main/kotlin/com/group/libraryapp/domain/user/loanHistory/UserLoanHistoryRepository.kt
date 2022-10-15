package com.group.libraryapp.domain.user.loanHistory

import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {
    // isReturn -> Enum class 변경했는데 이가 컴파일 에러로 잡히지 않는다 (Querydsl 적용하면서 해결 예정)
    fun findByBookNameAndStatus(bookName: String, status: UserLoanStatus) : UserLoanHistory?
    fun findAllByStatus(status: UserLoanStatus): List<UserLoanHistory>
    fun countByStatus(status: UserLoanStatus): Long
}