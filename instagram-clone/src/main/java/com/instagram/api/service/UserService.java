package com.instagram.api.service;

import com.instagram.api.dto.UserJoinReqDto;
import com.instagram.api.entity.User;
import com.instagram.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원가입
     * @param userJoinReqDto
     */
    public void addUser(UserJoinReqDto userJoinReqDto) {
        String encPassword = bCryptPasswordEncoder.encode(userJoinReqDto.getPassword());
        userJoinReqDto.setPassword(encPassword);

        User user = userJoinReqDto.toEntity();
        user.setRole("ROLE_USER");

        userRepository.save(user);
    }
}
