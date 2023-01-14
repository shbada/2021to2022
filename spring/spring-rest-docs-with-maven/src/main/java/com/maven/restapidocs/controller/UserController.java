package com.maven.restapidocs.controller;

import com.maven.restapidocs.dto.ResponseDto;
import com.maven.restapidocs.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    @GetMapping("/{userId}")
    public ResponseDto<UserDto> getUser(@PathVariable String userId){
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setUserName("테스트");
        userDto.setAge(27);

        return ResponseDto.<UserDto>builder()
                .status(HttpStatus.OK.value())
                .message("SUCCESS")
                .data(userDto)
                .build();
    }

    @GetMapping("/users")
    public ResponseDto<List<UserDto>> getUsers(){
        UserDto userDto = new UserDto();
        userDto.setUserId("test1");
        userDto.setUserName("테스트1");
        userDto.setAge(27);

        UserDto userDto2 = new UserDto();
        userDto2.setUserId("test2");
        userDto2.setUserName("테스트2");
        userDto2.setAge(25);

        List<UserDto> list = new ArrayList<>();
        list.add(userDto);
        list.add(userDto2);

        return ResponseDto.<List<UserDto>>builder()
                .status(HttpStatus.OK.value())
                .message("SUCCESS")
                .data(list)
                .build();
    }
}
