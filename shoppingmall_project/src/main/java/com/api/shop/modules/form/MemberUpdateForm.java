package com.api.shop.modules.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberUpdateForm {
    @NotNull
    private Long idx;

    @Length(min = 8, max = 50)
    private String password;
}
