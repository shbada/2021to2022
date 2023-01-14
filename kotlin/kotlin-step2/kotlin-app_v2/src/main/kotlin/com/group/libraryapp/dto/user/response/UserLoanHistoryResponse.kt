package com.group.libraryapp.dto.user.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanHistory.UserLoanStatus

// 코틀린에서는 위와 같이 한 파일에 여러 클래스를 만드는 것이 가능하다.
data class UserLoanHistoryResponse(
    val name: String, // 유저 이름
    val books: List<BookHistoryResponse>
) {
    companion object {
        fun of(user: User): UserLoanHistoryResponse {
            return UserLoanHistoryResponse(
                name = user.name,
                books = user.userLoanHistoryList.map(BookHistoryResponse.Companion::of)
            )
        }
    }
}

data class BookHistoryResponse(
    val name: String, // 책의 이름
    val isReturn: Boolean
) {
    companion object {
        fun of(userLoanHistory: UserLoanHistory): BookHistoryResponse {
            return BookHistoryResponse(
                name = userLoanHistory.bookName,
                isReturn = userLoanHistory.status == UserLoanStatus.RETURNED
            )
        }
    }
}