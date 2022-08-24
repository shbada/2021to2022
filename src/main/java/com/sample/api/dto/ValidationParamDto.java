package com.sample.api.dto;

import com.sample.api.dto.validator.groups.Client;
import com.sample.api.dto.validator.groups.ClientA;
import com.sample.api.dto.validator.groups.ClientB;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ValidationParamDto {
    @NotBlank(groups = {Client.class})
    private String userId;

    @NotBlank(groups = {ClientA.class})
    private String userName;

    @NotBlank(groups = {ClientA.class})
    private String addr;

    @Length(min = 10, max = 10, groups = ClientA.class)
    @Length(min = 5, max = 5, groups = ClientB.class)
    private String memo;
}
