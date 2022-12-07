package com.api.plan.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberLoginReqDto {
    private String memberId;
    private String password;
}
