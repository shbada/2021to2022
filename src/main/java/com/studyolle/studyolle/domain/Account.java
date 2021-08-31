package com.studyolle.studyolle.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
/** id 만 사용?
   -> 연관 관계가 복잡해 질 때, @EqualsAndHashCode 에서 서로 다른 연관관계를 순환 참조하느라 무한 루프가 발생하고,
      결국 stackoverflow 가 발생할 수 있기 때문에 id 값만 주로 사용
 1) equals   : 두 객체의 내용이 같은지 확인
 2) hashcode : 두 객체가 같은 객체인지 확인
 */
@EqualsAndHashCode(of = "id") /* equals, hashCode 자동 생성 */
@Builder
@AllArgsConstructor /* 모든 필드 값을 파라미터로 받는 생성자를 만들어준다 */
@NoArgsConstructor /* 파라미터가 없는 기본 생성자 생성 */
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    /* 이메일 검증 여부 */
    private boolean emailVerified;

    /* 이메일 검증 토큰값 */
    private String emailCheckToken;

    /* 인증 일자 */
    private LocalDateTime emailCheckTokenGeneratedAt;

    /* 가입 날짜 */
    private LocalDateTime joinedAt;

    /* 프로필 관련 간단한 소개글 */
    private String bio;

    private String url;

    /* 직업 */
    private String occupation;

    /* 위치 (지역) */
    private String location;

    @Lob /* varchar(255) */
    @Basic(fetch = FetchType.EAGER) /* default LAZY -> EAGER 명시적 설정 */
    /* 이미지는 유저를 로딩할때 같이 쓰일 예정이므로 EAGER */
    private String profileImage;

    /* 스터디 생성 이메일 알람 여부 */
    private boolean studyCreatedByEmail;

    private boolean studyCreatedByWeb = true;

    /* 스터디 모임의 가입신청 결과 이메일 알람 여부 */
    private boolean studyEnrollmentResultByEmail;

    private boolean studyEnrollmentResultByWeb = true;

    /* 스터디 모임의 변경사항 이메일 알람 여부 */
    private boolean studyUpdatedByEmail;

    private boolean studyUpdatedByWeb = true;
}
