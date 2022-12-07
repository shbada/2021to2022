package com.sp.fc.web.student;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * /student 도메인
 * Principal 역할
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private String id;
    private String username;

    @JsonIgnore
    private Set<GrantedAuthority> role; // 권한

    private String teacherId;
}
