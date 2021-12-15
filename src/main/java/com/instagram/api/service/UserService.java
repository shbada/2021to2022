package com.instagram.api.service;

import com.instagram.api.dto.UserJoinReqDto;
import com.instagram.api.entity.User;
import com.instagram.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * 회원가입
     * @param userJoinReqDto
     */
    public void addUser(UserJoinReqDto userJoinReqDto) {
        User user = userJoinReqDto.toEntity();
        userRepository.save(user);
    }
}
