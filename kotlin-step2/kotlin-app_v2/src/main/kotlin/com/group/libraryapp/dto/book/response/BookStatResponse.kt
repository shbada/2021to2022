package com.group.libraryapp.dto.book.response

import com.group.libraryapp.domain.book.BookType

data class BookStatResponse(
    val type: BookType,
    val count: Long, // query 에서 count 결과는 Long type
)
//) {
//    fun plusOne() {
//        this.count += 1
//    }
//}