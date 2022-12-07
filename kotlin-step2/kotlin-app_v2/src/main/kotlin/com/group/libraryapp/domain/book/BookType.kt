package com.group.libraryapp.domain.book

enum class BookType {
    COMPUTER,
    ECONOMY,
    SOCIETY,
    LANGUAGE,
    SCIENCE,
    ;
}

//enum class BookType(val score: Int) {
//    COMPUTER(10),
//    ECONOMY(8),
//    SOCIETY(5),
//    LANGUAGE(5),
//    SCIENCE(5),
//    ;
//
//    // Book.kt
//    fun getEventScore(): Int {
//        return this.type.score
//    }
//}