package com.seohae.java.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    @ApiParam(defaultValue = "seohae001")
    private String userId;

    @ApiParam(defaultValue = "김서해")
    private String userName; /* userName */

    @ApiParam(defaultValue = "27")
    private Integer userAge; /* age */

    @ApiParam(defaultValue = "F")
    private String userSex; /* sex */

    @ApiParam(defaultValue = "0212341234")
    private String telNo;

    @ApiParam(defaultValue = "01056875754")
    private String phone;
}
