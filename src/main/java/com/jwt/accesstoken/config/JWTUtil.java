package com.jwt.accesstoken.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jwt.accesstoken.user.domain.SpUser;

import java.time.Instant;

public class JWTUtil {
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("seohae");
    // private static final long AUTH_TIME = 20 * 60;
    private static final long AUTH_TIME = 2; /* 2초 였다면? 3초 후 요청은 토큰이 유효하지 않겠다. */
    private static final long REFRESH_TIME = 60 * 60 * 24 * 7;

    /**
     * auth token 생성
     * @param user
     * @return
     */
    public static String makeAuthToken(SpUser user) {
        // user 정보로부터 생성
        return JWT.create()
                .withSubject(user.getUsername()) /* user: 로그인한 사용자의 최소한의 정보를 넣는다 */
                .withClaim("exp", Instant.now().getEpochSecond() + AUTH_TIME)
                .sign(ALGORITHM);
    }

    /**
     * refresh token 생성
     * @param user
     * @return
     */
    public static String makeRefreshToken(SpUser user) {
        // user 정보로부터 생성
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("exp", Instant.now().getEpochSecond() + REFRESH_TIME)
                .sign(ALGORITHM);
    }

    /**
     * 토큰이 유효한지 체크
     */
    public static VerifyResult verify(String token) {
        try {
            DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);

            return VerifyResult.builder()
                    .success(true)
                    .username(verify.getSubject()) // get username
                    .build();
        } catch (Exception ex) {
            // token decode
            DecodedJWT decode = JWT.decode(token);

            return VerifyResult.builder()
                    .success(false)
                    .username(decode.getSubject()) // get username
                    .build();
        }
    }
}
