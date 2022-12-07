package com.jpa.bookmanager.domain;

public enum Gender {
    /* 중간에 추가되면 값이 틀어질 위험이 있음 (@Enumerated(value = EnumType.STRING)) */
    MALE, /* ENUM 의 순서가 첫번째일 경우 DB: 0 */
    FEMALE /* 두번째는 DB: 1 */
}
