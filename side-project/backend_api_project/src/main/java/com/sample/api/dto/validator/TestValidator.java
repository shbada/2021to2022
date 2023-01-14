package com.sample.api.dto.validator;

import com.sample.api.dto.ValidationParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class TestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ValidationParamDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationParamDto validationParamDto = (ValidationParamDto) target;
        log.info(validationParamDto.getUserId());

        if("test".equals(validationParamDto.getUserId())) {
            errors.rejectValue("userId", "userId", "userId이 test와 동일하다.");
        }
    }
}

