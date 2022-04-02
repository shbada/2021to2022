package com.api.shop.modules.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class OrderAddForm {
    @Min(1)
    private int memberIdx;

    @Min(1)
    private int itemIdx;

    @Min(1)
    private int itemCount;

    @NotBlank
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String city;

    @NotBlank
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String street;

    @NotBlank
    @Length(min = 3, max = 10)
    @Pattern(regexp = "^[0-9]{3,10}$")
    private String zipcode;
}
