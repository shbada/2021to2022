package com.group.libraryapp.dto.book.request

import com.group.libraryapp.domain.book.BookType

class BookRequest(
    val name: String,
    val type: BookType
)