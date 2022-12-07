package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User

class UserResponse(
    val id: Long,
    val name: String,
    val age: Int?,
) {
    // 정적팩토리
    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(
                id = user.id!!, // null 불가능
                name = user.name,
                age = user.age
            )
        }
    }

    // 생성자
//    constructor(user: User): this (
//        id = user.id!!, // null 불가능
//        name = user.name,
//        age = user.age
//    )

    // init
//    init {
//        id = user.getId()
//        name = user.getName()
//        age = user.getAge()
//    }
}