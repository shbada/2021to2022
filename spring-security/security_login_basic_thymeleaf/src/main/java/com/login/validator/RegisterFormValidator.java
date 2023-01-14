package com.login.validator;

import com.login.form.RegisterForm;
import com.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RegisterFormValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(RegisterForm.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        RegisterForm registerForm = (RegisterForm) object;

        // userId check
        if (userRepository.existsByUserId(registerForm.getUserId())) {
            errors.rejectValue("userId", "invalid.userId", new Object[]{registerForm.getUserId()}, "이미 사용중인 아이디입니다.");
        }
    }
}
