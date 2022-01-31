package com.api.shop.api.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class LoginForm {
    @NotBlank
    @Length(min = 3, max = 20)
    private String memberId;

    @NotBlank
    @Length(min = 8, max = 50)
    private String password;
}
