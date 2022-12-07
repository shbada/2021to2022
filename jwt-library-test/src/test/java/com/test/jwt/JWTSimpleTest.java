package com.test.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JWTSimpleTest {
    private void printToken(String token) {
        // token 을 꺼내볼 수 있다.
        String[] tokens = token.split("\\.");
        System.out.println("header: "+new String(Base64.getDecoder().decode(tokens[0])));
        System.out.println("body: "+new String(Base64.getDecoder().decode(tokens[1])));
    }

    /**
     * JWT 클라이언트가 어디든간에, 알고리즘과 key 값이 서로 일치한다면
     * 누구든 검증 가능한 토큰이다.
     */

    @DisplayName("1. jwt 를 이용한 토큰 테스트")
    @Test
    void test_1(){
        String okta_token = Jwts.builder().addClaims(Map.of("name", "seohae", "age", "26"))
                .signWith(SignatureAlgorithm.HS256, "seohae")
                .compact(); /* to token */

        // eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoic2VvaGFlIiwiYWdlIjoiMjYifQ.ReYj3sNUgRc3uBwm07Rt3bSZcDiXtIqAqWpJC5bzjEI
        System.out.println(okta_token);

        // header: {"alg":"HS256"}
        // body  : {"age":"26","name":"seohae"}
        printToken(okta_token);

        // header={alg=HS256},body={name=seohae, age=26},signature=ReYj3sNUgRc3uBwm07Rt3bSZcDiXtIqAqWpJC5bzjEI
        Jws<Claims> tokenInfo = Jwts.parser().setSigningKey("seohae").parseClaimsJws(okta_token);
        System.out.println(tokenInfo);
    }

    @DisplayName("2. java-jwt 를 이용한 토큰 테스트")
    @Test
    void test_2() {
        /**
         * key 값을 같게하기 위해
         */
        byte[] SEC_KEY = DatatypeConverter.parseBase64Binary("seohae");

        String oauth0_token = JWT.create().withClaim("name", "seohae")
                .withClaim("age", "26")
                .sign(Algorithm.HMAC256(SEC_KEY));

        // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoic2VvaGFlIiwiYWdlIjoiMjYifQ.Mp2RZFtcj8ieOScsl7H3MY27yy0aF1aD4euT342YNoI
        System.out.println(oauth0_token);

        // header: {"typ":"JWT","alg":"HS256"}
        // body  : {"name":"seohae","age":"26"}
        printToken(oauth0_token);

        // {name="seohae", age="26"}
        DecodedJWT verified = JWT.require(Algorithm.HMAC256(SEC_KEY)).build().verify(oauth0_token);
        System.out.println(verified.getClaims());

        // header={typ=JWT, alg=HS256},body={name=seohae, age=26},signature=zN1HW8QaMh1wCsvapeivwiSvJ0M5tPh_Ww6jRDdTl24
        Jws<Claims> tokenInfo = Jwts.parser().setSigningKey(SEC_KEY).parseClaimsJws(oauth0_token);
        System.out.println(tokenInfo);
    }

    @DisplayName("3. 만료 시간 테스트")
    @Test
    void test_3() throws InterruptedException {
        final Algorithm AL = Algorithm.HMAC256("seohae");

        String token = JWT.create().withSubject("a1234")
                /* 유효 시작 시간 (1초는 지나야 유효해진다.) */
                .withNotBefore(new Date(System.currentTimeMillis() + 1000))
                /* 만료 시간 (현재시간부터 3초간 토큰이 유효하다) */
                .withExpiresAt(new Date(System.currentTimeMillis() + 3000))
                .sign(AL);

        // Thread.sleep(2000);

        try {
            DecodedJWT verify = JWT.require(AL).build().verify(token);

            // verify : {sub="a1234", nbf=1639306749, exp=1639306751}
            System.out.println("verify : " + verify.getClaims());
        } catch(Exception ex) {
            System.out.println("유효하지 않은 토큰입니다...");

            // {sub="a1234", nbf=1639306637, exp=1639306639}
            DecodedJWT decode = JWT.decode(token);
            System.out.println("decode : " + decode.getClaims());
        }

    }
}
