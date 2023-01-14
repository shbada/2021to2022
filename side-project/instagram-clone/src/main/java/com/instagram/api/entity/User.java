package com.instagram.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    private String email;
    private String name; // 이름

    private String website; // 자기 홈페이지
    private String intro; // 자기소개
    private String phone; // 전화번호
    private String gender;
    private String profileImageUrl; // 프로필 이미지
    private String provider; // 제공자 Google, Facebook, Naver

    private String role; // USER, ADMIN
}
