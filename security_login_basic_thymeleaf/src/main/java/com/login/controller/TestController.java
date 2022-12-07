package com.login.controller;

import com.login.entity.Users;
import com.login.repository.UserRepository;
import com.login.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<Users> getUserList() {
        return userRepository.findAll();
    }

    @GetMapping("/session")
    public Users getSessionUser(@CurrentUser Users users) {
        return users;
    }
}
