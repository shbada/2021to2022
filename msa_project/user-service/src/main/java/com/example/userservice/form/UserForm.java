package com.example.userservice.form;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserForm {
    @NotEmpty(message = "회원 아이디는 필수 입니다") /* 컨트롤러 @Valid 어노테이션으로 체크를 해준다 */
    @ApiParam(defaultValue = "seohae001")
    private String userId;

    @NotEmpty(message = "회원 이름은 필수 입니다")
    @ApiParam(defaultValue = "김서해")
    private String userName; /* userName */

    @Min(value = 0, message = "회원 나이는 필수 입니다")
    @ApiParam(defaultValue = "27")
    private Integer userAge; /* age */

    @NotEmpty(message = "회원 성별은 필수 입니다")
    @ApiParam(defaultValue = "F")
    private String userSex; /* sex */

    @NotEmpty(message = "회원 전화번호는 필수 입니다")
    @ApiParam(defaultValue = "0212341234")
    private String telNo;

    @NotEmpty(message = "회원 휴대폰번호는 필수 입니다")
    @ApiParam(defaultValue = "01056875754")
    private String phone;
}
