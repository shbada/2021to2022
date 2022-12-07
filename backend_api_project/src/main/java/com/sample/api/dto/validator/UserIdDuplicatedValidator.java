package com.sample.api.dto.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIdDuplicatedValidator implements ConstraintValidator<UserIdDuplicatedTarget, String> {

    @Override
    public boolean isValid(String userId, ConstraintValidatorContext constraintValidatorContext) {
        if ("test".equals(userId)) {
            return false;
        }

        return true;
    }
}
