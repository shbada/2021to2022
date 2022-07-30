package com.studyolle.modules.account;

import com.studyolle.modules.tag.Tag;
import com.studyolle.modules.zone.Zone;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true) /* 중복 불가능 */
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    /* 이메일 검증 */
    private boolean emailVerified;

    private String emailCheckToken;

    private LocalDateTime emailCheckTokenGeneratedAt;

    private LocalDateTime joinedAt; // 가입일자

    private String bio; // 자기소개

    private String url;

    private String occupation; // 직업

    private String location; // 지역 varchar(255) 기본 매핑

    @Lob /* CLOB */
    @Basic(fetch = FetchType.EAGER) /* 즉시로딩 */
    private String profileImage; // 프로필 이미지

    private boolean studyCreatedByEmail; // 스터디 생성 여부를 이메일로 받을것인가?

    private boolean studyCreatedByWeb; // 스터디 생성 여부를 웹으로 받을것인가?

    private boolean studyEnrollmentResultByEmail; // 스터디 모임 가입신청 결과를 이메일로 받을것인가?

    private boolean studyEnrollmentResultByWeb; // 스터디 모임 가입신청 결과를 웹으로 받을것인가?

    private boolean studyUpdatedByEmail; // 스터디 변경 정보를 이메일로 받을것인가?

    private boolean studyUpdatedByWeb; // 스터디 변경 정보를 웹으로 받을것인가?

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    private Set<Zone> zones = new HashSet<>();

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

    public void completeSignUp() {
        this.emailVerified = true;
        this.joinedAt = LocalDateTime.now();
    }

    public boolean isValidToken(String token) {
        return this.emailCheckToken.equals(token);
    }

    public boolean canSendConfirmEmail() {
        return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
    }

}
