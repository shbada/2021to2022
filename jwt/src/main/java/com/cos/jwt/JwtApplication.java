package com.cos.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 jwt.io

 JWT - 서명된 토큰 (Json Web Token)

 JWT 구조
 - 헤더 (alg : HS256, typ : JWT)
 - 유효 탑재량
 - 서명
 각각을 Base64로 인코딩

 1) 클라이언트가 userid,password로 로그인 시도하여 서버에 요청됨
 2) 서버가 header, payLoad, signiture 을 만든다.
    헤더에는 HS256, PayLoad (username), signiture (Header + payLoad + 코스) : 코스는 서버만 알고있는 key값

 SHA256(해쉬) HMAC(시크릿 키를 포함하는 암호화 방식)
 3) 암호화를 해서 각각을 Base64로 인코딩하고 이를 클라이언트에 돌려준다.
 4) jwt 토큰을 어디서 들고있을까? 보통은 클라이언트의 로컬 스토리지에서 저장한다.
 5) 저장을 해놨다가 클라이언트에서 또다시 서버에 요청을 했을 때.
    이때 jwt 토큰을 실어보낸다. (로컬 스토리지에 저장되어있는걸 들고)
    서버는 이 토큰을 받아서 유효성 체크를 하여 인증처리한다. (검증)
    Signiture (header + payload + 코스) 이걸 암호화 해봐서 이게 넘겨온 토큰과 같은지 검증
    인증 유저 정보는 payLoad의 username 으로 DB 조회 가능
 */
@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

}
