package com.login.controller;

import com.login.entity.User;
import com.login.form.RegisterForm;
import com.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
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
    public List<User> getUserList() {
        return userRepository.findAll();
    }
}
