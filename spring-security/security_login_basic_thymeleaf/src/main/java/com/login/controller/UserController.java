package com.login.controller;

import com.login.entity.Users;
import com.login.form.RegisterForm;
import com.login.repository.UserRepository;
import com.login.validator.RegisterFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final RegisterFormValidator registerFormValidator;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * validation check
     * @param webDataBinder
     */
    @InitBinder("registerForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(registerFormValidator);
    }

    /**
     * 회원가입 화면 이동
     * @return
     */
    @GetMapping("/registerForm")
    public String registerForm(Model model) {
        model.addAttribute(new RegisterForm());

        return "registerForm";
    }

    /**
     * 회원가입
     * @param registerForm
     * @param errors
     * @return
     */
    @PostMapping("/signup")
    public String signUpSubmit(@Valid RegisterForm registerForm, Errors errors) {
        /* error 발생시 다시 화면으로 돌아간다 */
        if (errors.hasErrors()) {
            return "registerForm";
        }

        /* entity setting */
        Users users = Users.builder()
                .userId(registerForm.getUserId())
                .password(passwordEncoder.encode(registerForm.getPassword()))
                .build();

        userRepository.save(users);

        /* 가입 완료시, 메인페이지로 이동 */
        return "redirect:/";
    }
}
