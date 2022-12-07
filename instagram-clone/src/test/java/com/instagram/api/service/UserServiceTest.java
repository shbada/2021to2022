package com.instagram.api.service;

import com.instagram.api.dto.UserJoinReqDto;
import com.instagram.api.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("회원가입 성공")
    @Test
    void addUser_success() {
        UserJoinReqDto userJoinReqDto = UserJoinReqDto.builder()
                .username("kimseohae")
                .password("1234")
                .email("test@test.com")
                .name("김서해")
                .build();

        userService.addUser(userJoinReqDto);

        assertTrue(userRepository.existsByUsername("kimseohae"));
    }
}