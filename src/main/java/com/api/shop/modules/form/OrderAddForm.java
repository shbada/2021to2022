package com.api.shop.modules.form;

import com.api.shop.modules.entity.Address;
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

    private AddressForm addressForm;
}
