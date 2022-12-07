package com.api.plan.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long idx;
    private String memberId;
    private String password;
    private String memberName;
    private String email;
    private String phone;
    private String gender;
    private String age;
    private String jobName;
    private String role; // USER, ADMIN
}
