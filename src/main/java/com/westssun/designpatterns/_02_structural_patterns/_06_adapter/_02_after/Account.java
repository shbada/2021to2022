package com.westssun.designpatterns._02_structural_patterns._06_adapter._02_after;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Account { // 기존 코드를 수정해도 된다면 기존 파일에 implements UserDetails 하여 처리할 수 있다.

    private String name;

    private String password;

    private String email;

}
