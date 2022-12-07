package com.api.shop.modules.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CartAddForm {
    @Min(1)
    private Long itemIdx;

    @Min(1)
    private Long memberIdx;

    @Min(1)
    private int count;
}
