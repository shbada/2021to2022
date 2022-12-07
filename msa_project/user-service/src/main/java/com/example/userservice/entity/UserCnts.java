package com.example.userservice.entity;

import com.example.userservice.common.enums.EnumUserCntsType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_cnts")
@Getter
public class UserCnts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCntsIdx; /* pk */

    @Column(name = "cnts_type", nullable = false)
    private String cntsType; /* 10: 연락처, 20: 주소지 전화번호 */

    @Column(name = "tel_no", nullable = false)
    private String telNo;

    @Column(name = "phone", nullable = false)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY) // 기본이 즉시로딩이므로, 지연로딩으로 설정하자.
    // @ManyToOne(fetch = FetchType.EAGER) // 즉시로딩 : Order 조회시 Member 를 항상 조인하여 함께 가져온다. (무조건 지연로딩을 사용하자(LAZY))
    // @XToOne(OneToOne, ManyToOne) 관계는 기본이 즉시로딩이므로 지연로딩으로 설정하자.
    @JoinColumn(name = "user_idx")
    @Setter
    private Users users;

    public UserCnts(String cntsType, String telNo, String phone) {
        this.cntsType = cntsType;
        this.telNo = telNo;
        this.phone = phone;
    }

    public UserCnts() {

    }

    /** 유저 생성시 실행되는 연락처 생성 */
    public static UserCnts createUserCnts(Users users, String telNo, String phone) {
        UserCnts userCnts = new UserCnts(EnumUserCntsType.BASIC_CNTS.getCode(), telNo, phone);
        userCnts.setUsers(users);
        return userCnts;
    }
}
