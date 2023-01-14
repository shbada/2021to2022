package com.group.libraryapp.domain.user

enum class UserStatus {
    ACTIVE,
    IN_ACTIVE,
    DELETED // 탈퇴 유저에 대한 요구사항 추가 (Enum 관리시, 이렇게 필드만 추가하면 된다.)
}