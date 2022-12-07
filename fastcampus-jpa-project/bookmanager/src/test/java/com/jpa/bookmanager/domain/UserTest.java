package com.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void test() {
        User user = new User();
        user.setEmail("seohae@naver.com");
        user.setName("test");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        /* 해시코드 노출 : User@4e1a6aeb */
        /* Object.toString()이 호출되기 때문 (user 호출 = user.toString() 호출) */
        System.out.println(user);

        User test = User.builder().name("test").email("test@test.com").build();
        System.out.println(test.getEmail());
    }
}