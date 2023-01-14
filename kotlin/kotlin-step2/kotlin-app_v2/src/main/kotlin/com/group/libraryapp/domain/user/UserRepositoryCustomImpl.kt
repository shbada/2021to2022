package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.user.QUser.user
import com.group.libraryapp.domain.user.loanHistory.QUserLoanHistory.userLoanHistory
import com.querydsl.jpa.impl.JPAQueryFactory

class UserRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory,
) : UserRepositoryCustom {
    /*
        - select(user) : select user
        - distinct() : select 결과에 DISTINCT를 추가한다.
        - from(user) : from user
        - leftJoin(userLoanHistory) : left join user_loan_history
        - on(userLoanHistory.user.id.eq(user.id)) : on user_loan_history.user_id = user.id
        - fetchJoin : 앞의 join을 fetch join으로 처리한다.
        - fetch() : 쿼리를 실행하여 결과를 List 로 가져온다.
     */
    override fun findAllWithHistories(): List<User> {
        return queryFactory.select(user).distinct()
            .from(user)
            .leftJoin(userLoanHistory).on(userLoanHistory.user.id.eq(user.id)).fetchJoin()
            .fetch()
    }
}