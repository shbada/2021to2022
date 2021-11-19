package com.api.westmall.form;

import com.api.westmall.entity.Gender;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberForm {
    @NotBlank
    private String userId;

    @NotBlank
    @Length(min = 8, max = 50)
    private String password;

    @NotBlank
    private String userName;

    private Gender gender;

    @Email
    @NotBlank
    private String email;
}
