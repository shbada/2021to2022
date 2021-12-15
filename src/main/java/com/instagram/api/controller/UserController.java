package com.instagram.api.controller;

import com.instagram.api.common.response.CommonResponse;
import com.instagram.api.dto.UserJoinReqDto;
import com.instagram.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final CommonResponse commonResponse;
    private final UserService userService;

    /**
     * 회원가입
     * @param userJoinReqDto
     * @return
     */
    @PostMapping("join")
    public ResponseEntity<?> addUser(@RequestBody UserJoinReqDto userJoinReqDto) {
        userService.addUser(userJoinReqDto);
        return commonResponse.send();
    }
}
