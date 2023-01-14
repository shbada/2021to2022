package com.group.libraryapp.repository

import com.group.libraryapp.domain.user.loanHistory.QUserLoanHistory.userLoanHistory
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanHistory.UserLoanStatus
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class UserLoanHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {
    fun find(bookName: String, status: UserLoanStatus? = null): UserLoanHistory? {
        return queryFactory.select(userLoanHistory)
            .from(userLoanHistory)
            .where( // where 조건에 여러 조건이 들어오면 각 조건은 AND 로 결합한다.
                userLoanHistory.bookName.eq(bookName),
                // status 추가를 유연하게 동일한 메서드에서 구현이 가능하다
                // ?.let : status 파라미터가 null인 경우에는 where 조건에 user_loan_history.status = status 가 들어가지 않도록 하였다.
                status?.let { userLoanHistory.status.eq(status) }
            )
            .limit(1) // 모든 검색 결과에서 1개만을 가져온다
            .fetchOne() // Entity 하나만으로 조회 결과를 반환
    }

//    fun find(bookName: String): UserLoanHistory? {
//        return queryFactory.select(userLoanHistory)
//            .from(userLoanHistory)
//            .where(
//                userLoanHistory.bookName.eq(bookName)
//            )
//            .limit(1) // 모든 검색 결과에서 1개만을 가져온다
//            .fetchOne() // Entity 하나만으로 조회 결과를 반환
//    }

    fun count(status: UserLoanStatus): Long {
        return queryFactory.select(userLoanHistory.count())
            .from(userLoanHistory)
            .where(
                userLoanHistory.status.eq(status)
            )
            // count의 결과는 숫자 1개이므로 fetchOne() 을 사용해준다.
            // 혹시나 결과가 비어 있다면 0L을 반환하도록 elivs 연산자를 사용한다.
            .fetchOne() ?: 0L
    }
}