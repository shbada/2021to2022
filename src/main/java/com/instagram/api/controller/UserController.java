package com.instagram.api.controller;

import com.instagram.api.common.exception.BadRequestException;
import com.instagram.api.common.response.CommonResponse;
import com.instagram.api.config.auth.PrincipalDetails;
import com.instagram.api.dto.UserDto;
import com.instagram.api.dto.UserJoinReqDto;
import com.instagram.api.dto.UserLoginReqDto;
import com.instagram.api.entity.User;
import com.instagram.api.repository.UserRepository;
import com.instagram.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final CommonResponse commonResponse;
    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * 회원가입
     * @param userJoinReqDto
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity<?> addUser(@RequestBody UserJoinReqDto userJoinReqDto) {
        userService.addUser(userJoinReqDto);
        return commonResponse.send(userRepository.findByUsername(userJoinReqDto.getUsername()));
    }

    /**
     * 사용자 정보 조회
     * @param principalDetails
     * @return
     */
    @GetMapping("/load")
    public ResponseEntity<?> loadUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails != null) { // 사용자가 있을 경우 사용자 정보를 리턴
            User user = principalDetails.getUser();

            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .name(user.getName())
                    .profileImageUrl(user.getProfileImageUrl())
                    .role(user.getRole())
                    .build();

            return commonResponse.send(userDto);
        } else {
            throw new BadRequestException("NOT FOUND USER");
        }
    }
}
