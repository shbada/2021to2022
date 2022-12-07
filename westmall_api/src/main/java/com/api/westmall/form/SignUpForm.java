package com.api.westmall.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SignUpForm {
    @Email
    @NotBlank
    private String userId;

    @NotBlank
    @Length(min = 8, max = 50)
    private String password;

}
